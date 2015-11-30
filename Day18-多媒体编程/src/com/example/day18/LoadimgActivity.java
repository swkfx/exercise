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
		// 创建原图
		bitmap_src = BitmapFactory.decodeResource(getResources(),
				R.drawable.tp1);
		iv_src.setImageBitmap(bitmap_src);
		iv_copy.setImageBitmap(bitmap_src);

	}

	public void reduce(View view) {
		// 创建一份空的拷贝,与原图宽高一致,设置一致.
		Bitmap bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		// 创建画板
		Canvas canvas = new Canvas(bitmap_copy);
		// 创建画笔
		Paint paint = new Paint();
		// 设置矩阵(改变位图的相关显示)
		Matrix matrix = new Matrix();

		x = x - 0.1f;
		if (x < 0.5) {
			x = 0.5f;
			Toast.makeText(this, "不能再小了..真的", Toast.LENGTH_SHORT).show();
		}
		System.out.println(x);
		matrix.setScale(x, x); // 设置缩小的幅度
		// 开始作画
		canvas.drawBitmap(bitmap_src, matrix, paint);
		// 显示图像
		iv_copy.setImageBitmap(bitmap_copy);
	}

	public void magnify(View view) {
		// 获取要改变位图的信息
		Bitmap bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		// 准备画板
		Canvas canvas = new Canvas(bitmap_copy);
		// 准备画笔
		Paint paint = new Paint();
		// 准备矩阵 (放大)
		Matrix matrix = new Matrix();
		x = x + 0.1f;
		if (x > 1.5) {
			x = 1.5f;
			Toast.makeText(this, "不能再大了..真的", Toast.LENGTH_SHORT).show();
		}
		matrix.setScale(x, x);
		// 开始画
		canvas.drawBitmap(bitmap_src, matrix, paint);
		// 显示位图
		iv_copy.setImageBitmap(bitmap_copy);

	}

}
