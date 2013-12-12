package com.example.scrapbook;

import java.util.ArrayList;

import java.util.HashMap;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity<ContactImageAdapter> extends Activity {
	 protected static final Activity view = null;
	 ImageButton Addscrap;
	    String[] sqliteIds;
	    ListView dataList;
        DataBaseHandler db;
        String[] values;
        Cursor c1;
        private static final String KEY_NAME1 = "name";
        private static final String KEY_DES1 = "descrip";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 Addscrap = (ImageButton) findViewById(R.id.btnAddSite);
		 dataList = (ListView) findViewById(R.id.list);
		 ArrayList<HashMap<String, String>> scraplist = new ArrayList<HashMap<String, String>>();
		 db = new DataBaseHandler(this);
		 
		 Addscrap.setOnClickListener(new View.OnClickListener() {
             
	            public void onClick(View v) {
	                Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
	               // startActivityForResult(i, 100);
	                startActivity(i);
	            }
	        });
		 db.open();
		 int k = db.gettCount();
		 if(k!=0){
		 for( int i = 1; i<k;i++){
			 
			 String tt = db.getName(i);
			// String dd = db.getDes(i);
			 
			 HashMap<String, String> map = new HashMap<String, String>();
             
             // adding each child node to HashMap key => value
             map.put(KEY_NAME1, tt);
            // map.put(KEY_DES1, dd);
             
             scraplist.add(map);	 
		 }
		 db.close();
		 }else{
			 (Toast.makeText(MainActivity.this, "no data", Toast.LENGTH_LONG)).show();
		 } 
		 
		 
		/* ListAdapter adapter = new SimpleAdapter(this, scraplist,
	                R.layout.main_activity_listnames,
	                new String[] { KEY_TITLE1 }, new int[] {
	                        R.id.tvtitle1});*/
         
		 
		 
		 dataList.setOnItemClickListener(new OnItemClickListener() {

			   @Override
			   public void onItemClick(AdapterView<?> parent, View v,
			     final int position, long id) {
				   String tit = ((TextView) view.findViewById(R.id.tvtitle1)).getText().toString();
		    //      String did = ((TextView) view.findViewById(R.id.tvdes1)).getText().toString();
			    Intent in = new Intent(MainActivity.this,
			      ShowActivity.class);
			    in.putExtra(KEY_NAME1, tit);
               // in.putExtra(KEY_DES1, did);
			    startActivity(in);
			   }
			   });
		 
	}
	
	
	
		 
		/* ListAdapter adapter = new SimpleAdapter(
                 MainActivity.this,
                 values, R.layout.main_activity_listnames,
                 new String[] { KEY_TITLE },
                 new int[] { R.id.titlel });
                 dataList.setAdapter(adapter);  */
		// values = db.getCnt();
	/*	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                 getBaseContext(), android.R.layout.simple_list_item_1,
                 android.R.id.text1, values);

         // Assign adapter to ListView
         dataList.setAdapter(adapter);
		 
	    } */
	     


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        // if result code 100
	        if (resultCode == 100) {
	            // reload this screen again
	            Intent intent = getIntent();
	            finish();
	            startActivity(intent);
	            (Toast.makeText(MainActivity.this, "Entered", Toast.LENGTH_LONG)).show();
	        }
	        else{
	        	(Toast.makeText(MainActivity.this, "Not Entered", Toast.LENGTH_LONG)).show();
	        }
	    } 
	 
	 public void onCreateContextMenu(ContextMenu menu, View v,
		        ContextMenuInfo menuInfo) {
		      if (v.getId()==R.id.list) {
		        menu.setHeaderTitle("Delete");
		            menu.add(Menu.NONE, 0, 0, "Delete Feed");
		      }
		    } 
	 
	 public boolean onContextItemSelected(MenuItem item) {
	      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	      int menuItemIndex = item.getItemId();
	      // check for selected option
	      if(menuItemIndex == 0){
	          DataBaseHandler Db1 = new DataBaseHandler(this);
	          int id1 = Integer.parseInt(sqliteIds[info.position]);
	          Db1.deleteEntry(id1);
	          Intent intent = getIntent();
	          finish();
	          startActivity(intent);
	      }
	       
	      return true;
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
