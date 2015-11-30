package com.example.day18;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

/**
 * <p>
 * Title:Scrawlv2Activity
 * </p>
 * 
 * <pre>
 * 1.增加了设置菜单.
 * 2.可以保存图像
 * 3.可以清空画布. ---实现,但是不知道对不对.
 * 4.修正了画笔位置   ---实现
 * 5.是画布在屏幕剩余位置平铺.---实现
 * 6.通过画路径来实现画线条---未实现.
 * 
 * </pre>
 * 
 * @author SwkFx
 * 
 */
public class Scrawlv2Activity extends Activity implements OnClickListener {
	private Canvas canvas;
	private Paint paint;
	private Bitmap bitmap;
	private ImageView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_scrawlv2);
		findViewById(R.id.color_scrawl_red).setOnClickListener(this);
		findViewById(R.id.color_scrawl_green).setOnClickListener(this);
		findViewById(R.id.color_scrawl_blue).setOnClickListener(this);
		findViewById(R.id.color_scrawl_yellow).setOnClickListener(this);
		findViewById(R.id.color_scrawl_purple).setOnClickListener(this);
		SeekBar seekBar = (SeekBar) findViewById(R.id.sb_scrawl_degree);
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setAntiAlias(true);// 是否抗锯齿.
		paint.setStyle(Paint.Style.STROKE);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// 停止的是时候.
				// 设置画笔的粗细.
				paint.setStrokeWidth(seekBar.getProgress());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
		content = (ImageView) findViewById(R.id.iv_scrawl_content2);
		/*设置imageview铺满剩余位置*/
		ViewTreeObserver observer = content.getViewTreeObserver();// 拿到view树的观察者
		observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				int width = content.getWidth();// 实际宽度
				int mwidth = content.getMaxWidth();// 测量宽度.

				int height = content.getHeight(); // 需要SDK 16
				int mheight = content.getMeasuredHeight();
				System.out.println("实际:" + width + "---" + height);
				System.out.println("测量:" + mwidth + "---" + mheight);
				bitmap = Bitmap.createBitmap(width, height,
						Bitmap.Config.ARGB_8888);
				// 把画布放置画板上
				canvas = new Canvas(bitmap);
				canvas.drawColor(Color.WHITE);// 设置画布颜色为白色.
			}
		});

		/*修正画笔位置错乱
		 WindowManager manager = (WindowManager)
		 getSystemService(WINDOW_SERVICE);
		 Display display = manager.getDefaultDisplay();
		 DisplayMetrics metrics = new DisplayMetrics();
		 display.getRealMetrics(metrics);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		density = metrics.density;// 获取密度(Dpi)的与QVGA的倍数.
		float dpi = metrics.densityDpi;
		System.out.println("dpi:" + dpi + "----" + density);*/

		// 设置画板监听
		content.setOnTouchListener(new OnTouchListener() {

			private float downX, downY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {// 选择触摸类型.
				case MotionEvent.ACTION_DOWN:// 当按下的时候
					downX = event.getX();
					downY = event.getY();
					System.out.println("按下的坐标:" + downX + ":" + downY);
					break;

				case MotionEvent.ACTION_MOVE:// 触摸状态是移动的时候
					float moveX = event.getX();
					float moveY = event.getY();
					// canvas.drawLine(downX, downY, moveX, moveY, paint);
					// canvas.drawPaint(paint);直接填充.
					Path path = new Path();
					path.lineTo(downX, downY);
					// path.quadTo(downX, downY, moveX, moveY);
					path.moveTo(moveX, moveY);
					canvas.drawPath(path, paint);
					// 画完必须设置显示的iv上.
					content.setImageBitmap(bitmap);
					// 还必须重新指定起点.
					downX = moveX;
					downY = moveY;

				default:
					break;
				}
				// 返回的是false ,代表的是这个事件我不处理，系统仍然把这个事件向下传递交给其他的子控件去处理。
				// 返回的是true , 代表的是这个事件到此结束，我们要自己消费(处理)这个事件了。
				return true;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_save:// 保存文件
			try {
				String path = "mnt/sdcard/" + System.currentTimeMillis()
						+ ".jpg";
				OutputStream stream = new FileOutputStream(path);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				Toast.makeText(Scrawlv2Activity.this, "保存成功", 0).show();

				// 保存好发布一个广播让系统扫描SD,让图片显示在系统的图库里.
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
				intent.setData(Uri.parse("file://"));
				sendBroadcast(intent);
				// 4.4以后需要使用新的 API

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case R.id.action_clean:// 清除画板
			// TODO

			canvas.drawColor(Color.WHITE);
			content.setImageBitmap(bitmap);
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onClick(View v) {
		// 选择颜色,设置画笔的颜色.
		switch (v.getId()) {
		case R.id.color_scrawl_red:
			Toast.makeText(this, "画笔的颜色是红色", Toast.LENGTH_SHORT).show();
			paint.setColor(Color.RED);
			break;
		case R.id.color_scrawl_green:
			Toast.makeText(this, "画笔的颜色是绿色", Toast.LENGTH_SHORT).show();
			paint.setColor(Color.GREEN);
			break;
		case R.id.color_scrawl_blue:
			Toast.makeText(this, "画笔的颜色是蓝色", Toast.LENGTH_SHORT).show();
			paint.setColor(Color.BLUE);
			break;
		case R.id.color_scrawl_yellow:
			Toast.makeText(this, "画笔的颜色是黄色", Toast.LENGTH_SHORT).show();
			paint.setColor(Color.YELLOW);
			break;
		case R.id.color_scrawl_purple:
			Toast.makeText(this, "画笔的颜色是紫色", Toast.LENGTH_SHORT).show();
			paint.setColor(0xffff00ff);
			break;
		}
	}
}
