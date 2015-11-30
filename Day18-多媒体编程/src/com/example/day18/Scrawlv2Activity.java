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
 * 1.���������ò˵�.
 * 2.���Ա���ͼ��
 * 3.������ջ���. ---ʵ��,���ǲ�֪���Բ���.
 * 4.�����˻���λ��   ---ʵ��
 * 5.�ǻ�������Ļʣ��λ��ƽ��.---ʵ��
 * 6.ͨ����·����ʵ�ֻ�����---δʵ��.
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
		paint.setAntiAlias(true);// �Ƿ񿹾��.
		paint.setStyle(Paint.Style.STROKE);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// ֹͣ����ʱ��.
				// ���û��ʵĴ�ϸ.
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
		/*����imageview����ʣ��λ��*/
		ViewTreeObserver observer = content.getViewTreeObserver();// �õ�view���Ĺ۲���
		observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				int width = content.getWidth();// ʵ�ʿ��
				int mwidth = content.getMaxWidth();// �������.

				int height = content.getHeight(); // ��ҪSDK 16
				int mheight = content.getMeasuredHeight();
				System.out.println("ʵ��:" + width + "---" + height);
				System.out.println("����:" + mwidth + "---" + mheight);
				bitmap = Bitmap.createBitmap(width, height,
						Bitmap.Config.ARGB_8888);
				// �ѻ������û�����
				canvas = new Canvas(bitmap);
				canvas.drawColor(Color.WHITE);// ���û�����ɫΪ��ɫ.
			}
		});

		/*��������λ�ô���
		 WindowManager manager = (WindowManager)
		 getSystemService(WINDOW_SERVICE);
		 Display display = manager.getDefaultDisplay();
		 DisplayMetrics metrics = new DisplayMetrics();
		 display.getRealMetrics(metrics);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		density = metrics.density;// ��ȡ�ܶ�(Dpi)����QVGA�ı���.
		float dpi = metrics.densityDpi;
		System.out.println("dpi:" + dpi + "----" + density);*/

		// ���û������
		content.setOnTouchListener(new OnTouchListener() {

			private float downX, downY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {// ѡ��������.
				case MotionEvent.ACTION_DOWN:// �����µ�ʱ��
					downX = event.getX();
					downY = event.getY();
					System.out.println("���µ�����:" + downX + ":" + downY);
					break;

				case MotionEvent.ACTION_MOVE:// ����״̬���ƶ���ʱ��
					float moveX = event.getX();
					float moveY = event.getY();
					// canvas.drawLine(downX, downY, moveX, moveY, paint);
					// canvas.drawPaint(paint);ֱ�����.
					Path path = new Path();
					path.lineTo(downX, downY);
					// path.quadTo(downX, downY, moveX, moveY);
					path.moveTo(moveX, moveY);
					canvas.drawPath(path, paint);
					// �������������ʾ��iv��.
					content.setImageBitmap(bitmap);
					// ����������ָ�����.
					downX = moveX;
					downY = moveY;

				default:
					break;
				}
				// ���ص���false ,�����������¼��Ҳ�����ϵͳ��Ȼ������¼����´��ݽ����������ӿؼ�ȥ����
				// ���ص���true , �����������¼����˽���������Ҫ�Լ�����(����)����¼��ˡ�
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
		case R.id.action_save:// �����ļ�
			try {
				String path = "mnt/sdcard/" + System.currentTimeMillis()
						+ ".jpg";
				OutputStream stream = new FileOutputStream(path);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				Toast.makeText(Scrawlv2Activity.this, "����ɹ�", 0).show();

				// ����÷���һ���㲥��ϵͳɨ��SD,��ͼƬ��ʾ��ϵͳ��ͼ����.
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
				intent.setData(Uri.parse("file://"));
				sendBroadcast(intent);
				// 4.4�Ժ���Ҫʹ���µ� API

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case R.id.action_clean:// �������
			// TODO

			canvas.drawColor(Color.WHITE);
			content.setImageBitmap(bitmap);
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onClick(View v) {
		// ѡ����ɫ,���û��ʵ���ɫ.
		switch (v.getId()) {
		case R.id.color_scrawl_red:
			Toast.makeText(this, "���ʵ���ɫ�Ǻ�ɫ", Toast.LENGTH_SHORT).show();
			paint.setColor(Color.RED);
			break;
		case R.id.color_scrawl_green:
			Toast.makeText(this, "���ʵ���ɫ����ɫ", Toast.LENGTH_SHORT).show();
			paint.setColor(Color.GREEN);
			break;
		case R.id.color_scrawl_blue:
			Toast.makeText(this, "���ʵ���ɫ����ɫ", Toast.LENGTH_SHORT).show();
			paint.setColor(Color.BLUE);
			break;
		case R.id.color_scrawl_yellow:
			Toast.makeText(this, "���ʵ���ɫ�ǻ�ɫ", Toast.LENGTH_SHORT).show();
			paint.setColor(Color.YELLOW);
			break;
		case R.id.color_scrawl_purple:
			Toast.makeText(this, "���ʵ���ɫ����ɫ", Toast.LENGTH_SHORT).show();
			paint.setColor(0xffff00ff);
			break;
		}
	}
}
