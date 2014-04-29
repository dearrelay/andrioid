package com.firstandroid.activity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListViewActivity extends ListActivity {
	private static final String TAG = "ListViewActivity3";
	private ListView lv = null;
	private Cursor cursor = null;
	private int idCol = -1;
	private int nameCol = -1;
	private int timesContactedCol = -1; 
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.list);
		
		lv = this.getListView();
		
	    cursor = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null,ContactsContract.Contacts.DISPLAY_NAME+" ASC");
		String[] cols = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
		idCol = cursor.getColumnIndex(ContactsContract.Contacts._ID);
		nameCol = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
		timesContactedCol = cursor.getColumnIndex(ContactsContract.Contacts.TIMES_CONTACTED);
		int[] views = new int[]{android.R.id.text1};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_multiple_choice,cursor, cols,views,0);
		this.setListAdapter(adapter);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
	}
	
	public void doClick(View view){
		int count = lv.getCount();
		SparseBooleanArray viewItems = lv.getCheckedItemPositions();
		for(int i = 0 ; i < count ; i++){
			if(viewItems.get(i)){
				//CursorWrapper cw = (CursorWrapper) lv.getItemAtPosition(i);
				cursor.moveToPosition(i);
				long id = cursor.getLong(idCol);
				String name = cursor.getString(nameCol);
				int timesContacted = cursor.getInt(timesContactedCol);
				Log.v(TAG, name+"is chekced. Times contacted = "+ timesContacted +". Position = "+ i + ". Id = "+id);
//				Log.v(TAG, name+"is chekced.Position = "+ i + ". Id = "+id);
			}
		}
	}

}
