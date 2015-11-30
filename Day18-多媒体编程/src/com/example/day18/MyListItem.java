package com.example.day18;

import android.content.Context;
import android.content.Intent;

public class MyListItem {
	/**
	 * @param appName
	 * @param context
	 * @param intent
	 */
	public MyListItem(String appName, Context context, Intent intent) {
		super();
		this.appName = appName;
		this.context = context;
		this.intent = intent;
	}

	String appName;
	Context context;
	Intent intent;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	@Override
	public String toString() {
		return appName;
	}

}
