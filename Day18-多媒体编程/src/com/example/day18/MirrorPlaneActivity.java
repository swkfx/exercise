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

public class MirrorPlaneActivity extends Activity {
	ImageView iv_src, iv_copy;
	private File file;
	private Bitmap bitmap_src;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mirrorplane);
		iv_src = (ImageView) findViewById(R.id.iv_mirror_src);
		iv_copy = (ImageView) findViewById(R.id.iv_mirror_copy);
		file = new File("mnt/sdcard/tp2.jpg");
		if (!file.exists() || file.length() < 0) {
			Log.e("MirrorPlaneActivity", "��Դ�ļ�·�����Ի��߲�����!!");
			return;
		}
		bitmap_src = BitmapFactory.decodeFile(file.getPath());
		iv_src.setImageBitmap(bitmap_src);
		iv_copy.setImageBitmap(bitmap_src);

	}

	public void effect(View view) {

		Bitmap bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		Canvas canvas = new Canvas(bitmap_copy);
		Paint paint = new Paint();
		Matrix matrix = new Matrix();
		matrix.setScale(1, -1);
		matrix.postTranslate(0, bitmap_copy.getHeight());

		canvas.drawBitmap(bitmap_src, matrix, paint);
		iv_copy.setImageBitmap(bitmap_copy);

	}

	public void mirror(View view) {
		Bitmap bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		Canvas canvas = new Canvas(bitmap_copy);
		Paint paint = new Paint();
		Matrix matrix = new Matrix();
		// ����Ч��.����y����������.//�����ļ��Ѿ���������,Ҫ����λ�ô��ͻ���.
		matrix.setScale(-1, 1);
		matrix.postTranslate(bitmap_copy.getWidth(), 0);
		canvas.drawBitmap(bitmap_src, matrix, paint);
		iv_copy.setImageBitmap(bitmap_copy);

	}

}
