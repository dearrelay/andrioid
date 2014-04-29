package com.firstandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewCustomAdapter extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	//Custom Adapter
	private static class ManateeAdapter extends BaseAdapter{
		private static final String TAG = "ManateeAdapter";
		private static int convertViewCounter = 0;
		private Context mContext;
		private LayoutInflater mInflater;
		static class ViewHolder{
			ImageView image;
		}
		
	}

}
