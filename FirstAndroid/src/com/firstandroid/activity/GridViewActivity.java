package com.firstandroid.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

public class GridViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);
		GridView gv = (GridView)findViewById(R.id.gridview);
		
		Cursor c = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null,ContactsContract.Contacts.DISPLAY_NAME);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1
				                                      ,c,new String[]{ContactsContract.Contacts.DISPLAY_NAME}
		                                              ,new int[]{android.R.id.text1},0);
		gv.setAdapter(adapter);
		
	}
}
