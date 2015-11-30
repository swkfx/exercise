package com.example.day18;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LoadimgActivity extends Activity {
	ImageView iv_src;
	ImageView iv_copy;
	Bitmap bitmap_src;
	float x = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loadimg);
		iv_src = (ImageView) findViewById(R.id.iv_src);
		iv_copy = (ImageView) findViewById(R.id.iv_copy);
		// ����ԭͼ
		bitmap_src = BitmapFactory.decodeResource(getResources(),
				R.drawable.tp1);
		iv_src.setImageBitmap(bitmap_src);
		iv_copy.setImageBitmap(bitmap_src);

	}

	public void reduce(View view) {
		// ����һ�ݿյĿ���,��ԭͼ���һ��,����һ��.
		Bitmap bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		// ��������
		Canvas canvas = new Canvas(bitmap_copy);
		// ��������
		Paint paint = new Paint();
		// ���þ���(�ı�λͼ�������ʾ)
		Matrix matrix = new Matrix();

		x = x - 0.1f;
		if (x < 0.5) {
			x = 0.5f;
			Toast.makeText(this, "������С��..���", Toast.LENGTH_SHORT).show();
		}
		System.out.println(x);
		matrix.setScale(x, x); // ������С�ķ���
		// ��ʼ����
		canvas.drawBitmap(bitmap_src, matrix, paint);
		// ��ʾͼ��
		iv_copy.setImageBitmap(bitmap_copy);
	}

	public void magnify(View view) {
		// ��ȡҪ�ı�λͼ����Ϣ
		Bitmap bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		// ׼������
		Canvas canvas = new Canvas(bitmap_copy);
		// ׼������
		Paint paint = new Paint();
		// ׼������ (�Ŵ�)
		Matrix matrix = new Matrix();
		x = x + 0.1f;
		if (x > 1.5) {
			x = 1.5f;
			Toast.makeText(this, "�����ٴ���..���", Toast.LENGTH_SHORT).show();
		}
		matrix.setScale(x, x);
		// ��ʼ��
		canvas.drawBitmap(bitmap_src, matrix, paint);
		// ��ʾλͼ
		iv_copy.setImageBitmap(bitmap_copy);

	}

}
