package com.example.day18;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class RotateImgActivity extends Activity {
	ImageView iv_src, iv_copy;
	private Bitmap bitmap_src;
	private Bitmap bitmap_copy;
	private Matrix matrix;
	private Paint paint;
	private Canvas canvas;
	private File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rotate);
		iv_src = (ImageView) findViewById(R.id.iv_ratate_src);
		iv_copy = (ImageView) findViewById(R.id.iv_ratate_copy);
		file = new File("mnt/sdcard/tp2.jpg");
		if (!file.exists() || file.length() < 0) {
			Log.e("RotateImgActivity", "�ļ�������");
			return;

		}
		// ���ļ��л�ȡԭͼ
		bitmap_src = BitmapFactory.decodeFile(file.getPath());
		iv_src.setImageBitmap(bitmap_src);

	}

	public void rotate(View view) {

		// ��ȡԭͼ�������Ϣ.
		bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		// ��������
		canvas = new Canvas(bitmap_copy);
		// ��������
		paint = new Paint();
		// ��������(�ı�ͼƬ�Ĺ���)
		matrix = new Matrix();
		// matrix.setRotate(15);// ָ����ת�ĽǶ�(������ 0,0 ΪԲ����ת.ͼƬ�����Ͻ�)
		// ��ͼƬ������ΪԲ����ת.
		matrix.setRotate(180, bitmap_copy.getWidth() / 2,
				bitmap_copy.getHeight() / 2);
		canvas.drawBitmap(bitmap_src, matrix, paint);
		// ��ʾͼƬ
		iv_copy.setImageBitmap(bitmap_copy);

	}

	public void translation(View view) {

		// ��ȡԭͼ�������Ϣ.
		bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		// ��������
		canvas = new Canvas(bitmap_copy);
		// ��������
		paint = new Paint();
		matrix = new Matrix();
		matrix.setTranslate(0, 35);// x�����ƶ�40px,y�����ƶ�35
		canvas.drawBitmap(bitmap_src, matrix, paint);
		iv_copy.setImageBitmap(bitmap_copy);
	}
}
