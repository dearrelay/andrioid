package com.firstandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewCustomAdapter extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manatee);
		GridView gv = (GridView)findViewById(R.id.gridview);
		ManateeAdapter adapter = new ManateeAdapter(this);
		gv.setAdapter(adapter);
		
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
		private int[] manatees = {
				R.drawable.panda01,R.drawable.panda02, R.drawable.panda03,
				R.drawable.panda04,R.drawable.panda05, R.drawable.panda06,
				R.drawable.panda07,R.drawable.panda08, R.drawable.panda09, R.drawable.panda10
		};
		
		private Bitmap[] manateeImages = new Bitmap[manatees.length];
		private Bitmap[] manateeThumbs = new Bitmap[manatees.length];
		
		public ManateeAdapter(Context context){
			Log.v(TAG,"Constructing ManateeAdapter");
			this.mContext = context;
			mInflater = LayoutInflater.from(context);
			for(int i=0; i<manatees.length; i++){
				manateeImages[i] = BitmapFactory.decodeResource(context.getResources(), manatees[i]);
				manateeThumbs[i] = Bitmap.createScaledBitmap(manateeImages[i], 100, 100, false);
			}
		}
		//return how many objects to display
		@Override
		public int getCount(){
			Log.v(TAG, "in getCount()");
			return manatees.length;
		}
		//return how many view types to display
		public int getViewTypeCount(){
			Log.v(TAG, "getViewTypeCount ");
			return 1;
		}
		//return the type for different position object
		public int getItemViewType(int position) {
			Log.v(TAG, "in getItemViewType() for position " + position);
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			ViewHolder holder;
			Log.v(TAG, "in getView for position " + position +
					", convertView is " +
					((convertView == null)?"null":"being recycled"));
			
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.gridimage, null);
				convertViewCounter++;
				Log.v(TAG, convertViewCounter + " convertViews have been created");
				holder = new ViewHolder();
				holder.image = (ImageView) convertView.findViewById(R.id.imageitem);
				
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.image.setImageBitmap(manateeThumbs[position]);
			
			return convertView;
			
		}
		@Override
		public Object getItem(int position) {
		Log.v(TAG, "in getItem() for position " + position);
		return manateeImages[position];
		}
		@Override
		public long getItemId(int position) {
		Log.v(TAG, "in getItemId() for position " + position);
		return position;
		}
		
		
	}

}
