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
		paint.setAntiAlias(true);//�Ƿ񿹾��.
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
		// ���û����Ĵ�С
		final Bitmap bitmap = Bitmap.createBitmap(300, 300,
				Bitmap.Config.ARGB_8888);
		// �ѻ������û�����
		canvas = new Canvas(bitmap);

		final ImageView content = (ImageView) findViewById(R.id.iv_scrawl_content);

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
					canvas.drawLine(downX, downY, moveX, moveY, paint);
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
