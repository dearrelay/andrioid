package com.firstandroid.activity;

import com.firstandroid.fragment.DetailsFragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

public class ShowBooks extends Activity{
	public final static String TAG = "Book";
	public boolean isMultiPane(){
		return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	}
	
	public void showDetails(int index){
		Log.v(TAG,"in ShowBooks ("+index+")");
		
		if(isMultiPane()){
			DetailsFragment details = (DetailsFragment)getFragmentManager().findFragmentById(R.id.details);
			if((details == null) ||(details.getShownIndex() != index)){
				details = DetailsFragment.newInstance(index);
				// Execute a transaction, replacing any existing
				// fragment with this one inside the frame.
				Log.v(TAG, "about to run FragmentTransaction...");
				FragmentTransaction ft
				= getFragmentManager().beginTransaction();
				ft.setTransition(
						FragmentTransaction.TRANSIT_FRAGMENT_FADE);
						//ft.addToBackStack("details");
						ft.replace(R.id.details, details);
						ft.commit();
			}
		}
		else {
			// Otherwise you need to launch a new activity to display
			// the dialog fragment with selected text.
			Intent intent = new Intent();
			intent.setClass(this,ShowBooks.class);
			intent.putExtra("index", index);
			startActivity(intent);
			}
	}

}
