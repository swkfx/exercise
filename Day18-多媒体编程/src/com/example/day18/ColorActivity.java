package com.example.day18;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ColorActivity extends Activity implements OnSeekBarChangeListener {
	ImageView imageView;
	private Paint paint;
	private Matrix matrix;
	private Canvas canvas;
	private Bitmap bitmap_copy;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color);
		imageView = (ImageView) findViewById(R.id.iv_color_src);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tp1);
		bitmap_copy = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), bitmap.getConfig());
		canvas = new Canvas(bitmap_copy);
		paint = new Paint();
		matrix = new Matrix();
		// ����
		canvas.drawBitmap(bitmap, matrix, paint);
		imageView.setImageBitmap(bitmap_copy);

		SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
		SeekBar seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
		seekBar1.setOnSeekBarChangeListener(this);
		seekBar2.setOnSeekBarChangeListener(this);
		seekBar3.setOnSeekBarChangeListener(this);

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		// ����Ӧ��Ҳ�ǿ��Ե�����.
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	/**
	 * ������BUG,�������е�һ��ֻ��Ե�ǰ����״̬����,ֱ�Ӻ�������������.
	 * 
	 * Ӧ����һ���������������.�ֱ��ȡ3��seek��value ,Ȼ�󸳸�����.
	 * 
	 * 
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		switch (seekBar.getId()) {
		case R.id.seekBar1:
			int progress = seekBar.getProgress();
			float value = progress / 50.0f;// ����Ҫ��ȡ�м�ֵ.����������ʱ��������ֵ��Ӧ����1,
			ColorMatrix cm = new ColorMatrix();
			cm.set(new float[] { 1 * value, 0, 0, 0, 0, // ��ɫ
					0, 1, 0, 0, 0, // ��ɫ
					0, 0, 1, 0, 0,// ��ɫ
					0, 0, 0, 1, 0 // ͸����
			});
			// �������ù�����ɫ.
			paint.setColorFilter(new ColorMatrixColorFilter(cm));
			canvas.drawBitmap(bitmap, matrix, paint);
			imageView.setImageBitmap(bitmap_copy);
			break;
		case R.id.seekBar2:
			int progress1 = seekBar.getProgress();
			float value1 = progress1 / 50.0f;// ����Ҫ��ȡ�м�ֵ.����������ʱ��������ֵ��Ӧ����1,
			ColorMatrix cm1 = new ColorMatrix();
			cm1.set(new float[] { 1, 0, 0, 0, 0, // ��ɫ
					0, 1 * value1, 0, 0, 0, // ��ɫ
					0, 0, 1, 0, 0,// ��ɫ
					0, 0, 0, 1, 0 // ͸����
			});
			// �������ù�����ɫ.
			paint.setColorFilter(new ColorMatrixColorFilter(cm1));
			canvas.drawBitmap(bitmap, matrix, paint);
			imageView.setImageBitmap(bitmap_copy);
			break;
		case R.id.seekBar3:
			int progress2 = seekBar.getProgress();
			float value2 = progress2 / 50.0f;// ����Ҫ��ȡ�м�ֵ.����������ʱ��������ֵ��Ӧ����1,
			ColorMatrix cm2 = new ColorMatrix();
			cm2.set(new float[] { 1, 0, 0, 0, 0, // ��ɫ
					0, 1, 0, 0, 0, // ��ɫ
					0, 0, 1 * value2, 0, 0,// ��ɫ
					0, 0, 0, 1, 0 // ͸����
			});
			// �������ù�����ɫ.
			paint.setColorFilter(new ColorMatrixColorFilter(cm2));
			canvas.drawBitmap(bitmap, matrix, paint);
			imageView.setImageBitmap(bitmap_copy);
			break;

		}

	}

}
