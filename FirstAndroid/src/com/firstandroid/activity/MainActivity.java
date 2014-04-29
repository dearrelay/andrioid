package com.firstandroid.activity;


import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ListActivity implements OnItemClickListener {
	private LinearLayout nameContainer;
	private LinearLayout addressContainer;
	private LinearLayout parentContainer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * Complete Code for UI
		 * 
		 */
		
//		createNameContainer();
//		createAddressContainer();
//		createParentContainer();
//		setContentView(parentContainer);
		/*
		 * Complete Xml for UI
		 * 
		 */
//		setContentView(R.layout.main);
	    /**
	     * Combine Code and XML (recommended)
	     * 
	     */
		
		//simple controllers
		/*
		setContentView(R.layout.main);
		TextView nameValue = (TextView)findViewById(R.id.nameValue);
		nameValue.setText("Lei Yuan");
		TextView addrValue = (TextView)findViewById(R.id.addrValue);
		addrValue.setText("444 Washington Blvd.");
		
		AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.langValue);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				                                 android.R.layout.simple_dropdown_item_1line,
				                                 new String[]{"English","Chinese","Spanish","German","Greek"});
		actv.setAdapter(aa);
		
		MultiAutoCompleteTextView mactv = (MultiAutoCompleteTextView)findViewById(R.id.mactv);
		mactv.setAdapter(aa);
		mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
													R.array.planets, android.R.layout.simple_spinner_item);
	  
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		*/
		/**
		 * view lists and adapters
		 * 
		 */
		ListView lv = getListView();
		Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,ContactsContract.Contacts.DISPLAY_NAME+" asc");
		String[] cols = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1, c,cols,new int[]{android.R.id.text1},0);
		this.setListAdapter(adapter);
		lv.setOnItemClickListener(this);
		
	}
	
	
	private void createNameContainer(){
		nameContainer = new LinearLayout(this);
		nameContainer.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		nameContainer.setOrientation(LinearLayout.HORIZONTAL);
		TextView nameLbl = new TextView(this);
		nameLbl.setText("Name: ");
		TextView nameValue = new TextView(this);
		nameValue.setText("Lei Yuan");
		nameContainer.addView(nameLbl);
		nameContainer.addView(nameValue);
	}
	
	private void createAddressContainer(){
		addressContainer = new LinearLayout(this);
		addressContainer.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		addressContainer.setOrientation(LinearLayout.VERTICAL);
		TextView addrValue = new TextView(this);
		addrValue.setText("444 Washington Blvd.");
		TextView addrLbl = new TextView(this);
		addrLbl.setText("Address:");
		addressContainer.addView(addrLbl);
		addressContainer.addView(addrValue);
	}
	
	private void createParentContainer(){
		parentContainer = new LinearLayout(this);
		parentContainer.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		parentContainer.setOrientation(LinearLayout.VERTICAL);
		parentContainer.addView(nameContainer);
		parentContainer.addView(addressContainer);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		int base = Menu.FIRST;
		MenuItem item1 = menu.add(base, base, base, "Click Me");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		if(item.getItemId() == 1){
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://www.google.com"));
			this.startActivity(intent);
		}
		else{
			return super.onOptionsItemSelected(item);
		}
		return true;
		
	}
	
//    public void myClickHandler(View target){
//    	switch(target.getId()){
//    		case R.id.iconButton:
//    			Intent intent = new Intent(Intent.ACTION_VIEW);
//    			intent.setData(Uri.parse("https://www.google.com/#q=lei+yuan"));
//    			this.startActivity(intent);
//    		case R.id.steakCB:
//    			if(((CheckBox)target).isChecked()){
//    				Log.v("Steak Checkt", "Steak has been checked");
//    			}
//    	}
//    }
    
    @Override
    public void onItemClick(AdapterView<?> adView, View target, int position, long id) {
    Log.v("ListViewActivity", "in onItemClick with " + ((TextView) target).getText()
    +
    ". Position = " + position + ". Id = " + id);
    Uri selectedPerson = ContentUris.withAppendedId(
    		ContactsContract.Contacts.CONTENT_URI, id);
    Intent intent = new Intent(Intent.ACTION_VIEW, selectedPerson);
    startActivity(intent);
    }
}
