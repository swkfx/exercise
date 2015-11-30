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
			Log.e("RotateImgActivity", "文件不存在");
			return;

		}
		// 从文件中获取原图
		bitmap_src = BitmapFactory.decodeFile(file.getPath());
		iv_src.setImageBitmap(bitmap_src);

	}

	public void rotate(View view) {

		// 获取原图的相关信息.
		bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		// 创建画布
		canvas = new Canvas(bitmap_copy);
		// 创建画笔
		paint = new Paint();
		// 创建矩阵(改变图片的规则)
		matrix = new Matrix();
		// matrix.setRotate(15);// 指定旋转的角度(以坐标 0,0 为圆心旋转.图片的左上角)
		// 以图片的中心为圆点旋转.
		matrix.setRotate(180, bitmap_copy.getWidth() / 2,
				bitmap_copy.getHeight() / 2);
		canvas.drawBitmap(bitmap_src, matrix, paint);
		// 显示图片
		iv_copy.setImageBitmap(bitmap_copy);

	}

	public void translation(View view) {

		// 获取原图的相关信息.
		bitmap_copy = Bitmap.createBitmap(bitmap_src.getWidth(),
				bitmap_src.getHeight(), bitmap_src.getConfig());
		// 创建画布
		canvas = new Canvas(bitmap_copy);
		// 创建画笔
		paint = new Paint();
		matrix = new Matrix();
		matrix.setTranslate(0, 35);// x方向移动40px,y方向移动35
		canvas.drawBitmap(bitmap_src, matrix, paint);
		iv_copy.setImageBitmap(bitmap_copy);
	}
}
