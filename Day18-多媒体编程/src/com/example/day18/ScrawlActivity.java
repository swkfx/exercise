package com.example.day18;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class ScrawlActivity extends Activity implements OnClickListener {
	private Canvas canvas;
	private Paint paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scrawl);
		findViewById(R.id.color_scrawl_red).setOnClickListener(this);
		findViewById(R.id.color_scrawl_green).setOnClickListener(this);
		findViewById(R.id.color_scrawl_blue).setOnClickListener(this);
		findViewById(R.id.color_scrawl_yellow).setOnClickListener(this);
		findViewById(R.id.color_scrawl_purple).setOnClickListener(this);
		SeekBar seekBar = (SeekBar) findViewById(R.id.sb_scrawl_degree);
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setAntiAlias(true);//是否抗锯齿.
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
		// 设置画布的大小
		final Bitmap bitmap = Bitmap.createBitmap(300, 300,
				Bitmap.Config.ARGB_8888);
		// 把画布放置画板上
		canvas = new Canvas(bitmap);

		final ImageView content = (ImageView) findViewById(R.id.iv_scrawl_content);

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
					canvas.drawLine(downX, downY, moveX, moveY, paint);
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
