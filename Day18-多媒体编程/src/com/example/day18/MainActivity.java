package com.example.day18;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	ArrayAdapter<MyListItem> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new ArrayAdapter<MyListItem>(this,
				android.R.layout.simple_list_item_1);
		adapter.add(new MyListItem("1.加载图片到内存", this, new Intent(
				MainActivity.this, LoadimgActivity.class)));
		adapter.add(new MyListItem("2.位图的旋转与平移", this, new Intent(
				MainActivity.this, RotateImgActivity.class)));
		adapter.add(new MyListItem("3.位图镜面与倒影效果", this, new Intent(
				MainActivity.this, MirrorPlaneActivity.class)));
		adapter.add(new MyListItem("4.随手涂鸦", this, new Intent(
				MainActivity.this, ScrawlActivity.class)));
		adapter.add(new MyListItem("5.随手涂鸦v2", this, new Intent(
				MainActivity.this, Scrawlv2Activity.class)));
		adapter.add(new MyListItem("6.调色板", this, new Intent(MainActivity.this,
				ColorActivity.class)));
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		MyListItem item = adapter.getItem(position);
		Intent intent = item.getIntent();
		startActivity(intent);
	}

}
