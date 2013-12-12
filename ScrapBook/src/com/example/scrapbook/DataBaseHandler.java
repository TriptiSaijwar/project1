package com.example.scrapbook;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHandler {
	
	public  static final int DATABASE_VERSION = 1;
	public  static final String DATABASE_PATH = "data/data/com.example.scrapbook/databases/";
	
	public  static final String DATABASE_NAME = "scrapcontentdb";
	
	public  static final String DATABASE_TABLE1 = "memo";
	
	public static final String KEY_ID = "id";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_TITLE = "title";
	public static final String KEY_DES = "description";
	
	static SQLiteDatabase ourDatabase;
	static Context context;
	static Dbh ourHelper;
	
public class Dbh extends SQLiteOpenHelper {


	
	public Dbh(Context c) {
		// TODO Auto-generated constructor stub
		super(c, DATABASE_NAME, null, DATABASE_VERSION);
		context = c;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + DATABASE_TABLE1 + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "  + KEY_IMAGE + " TEXT," + KEY_TITLE
				+ " TEXT NOT NULL, " + KEY_DES + " TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);
		  onCreate(db);
	}
	
}
             public DataBaseHandler(Context r) {
	        context = r;

            }
	
	public DataBaseHandler open() {
		ourHelper = new Dbh(context);
        ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}
	
	 
	 public long createEntry(byte[] imageName,String title, String des){
				
			ContentValues cv = new ContentValues();
			cv.put(KEY_IMAGE, imageName);
			cv.put(KEY_TITLE, title);
			cv.put(KEY_DES, des);
			return ourDatabase.insert(DATABASE_TABLE1, null, cv);

		}
	 
	 public void updateEntry(int rowid,Byte img,String title, String des) {
			// TODO Auto-generated method stub


			ContentValues cv = new ContentValues();
			cv.put(KEY_IMAGE, img);
			cv.put(KEY_TITLE, title);
			cv.put(KEY_DES, des);
			ourDatabase.update(DATABASE_TABLE1, cv, KEY_ID + "=" + rowid, null);
		}
	 
	 public String getData() {

			// TODO Auto-generated method stub
			String[] columns = new String[] { KEY_ID, KEY_IMAGE, KEY_TITLE ,KEY_DES};
			Cursor c = ourDatabase.query(DATABASE_NAME, columns, null, null, null, null, null);
			String result = "";

			int irow = c.getColumnIndex(KEY_ID);
			int iimg = c.getColumnIndex(KEY_IMAGE);
			int ititle = c.getColumnIndex(KEY_TITLE);
			int ides = c.getColumnIndex(KEY_DES);

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result = result + c.getString(irow) + " "+c.getString(iimg) + " "+c.getString(ititle) + " "+c.getString(ides) + " "+"\n";

			}

			return result;
		}
	 
	 public String getName(int l){
		 String[] columns = new String[] { KEY_ID, KEY_IMAGE, KEY_TITLE ,KEY_DES};
		 Cursor c = ourDatabase.query(DATABASE_NAME, columns, KEY_ID + "=" + l, null, null, null, null);
		 if(c!=null){
			 c.moveToFirst();
			 String title1 = c.getString(2);
			 return title1;
		 }
		 return null;
	 }
	 
	/* public String getImage(int l){
		 Byte[] columns = new String[] { KEY_ID, KEY_IMAGE, KEY_TITLE ,KEY_DES};
		 Cursor c = ourDatabase.query(DATABASE_NAME, columns, KEY_ID + "=" + l, null, null, null, null, null);
		 if(c!=null){
			 c.moveToFirst();
			 String title1 = c.getString(2);
			 return title1;
		 }
		 return null;
	 } */
	 
	 public String getDes(int l){
		 String[] columns = new String[] { KEY_ID, KEY_IMAGE, KEY_TITLE ,KEY_DES};
		 Cursor c = ourDatabase.query(DATABASE_NAME, columns, KEY_ID + "=" + l, null, null, null, null, null);
		 if(c!=null){
			 c.moveToFirst();
			 String des1 = c.getString(3);
			 return des1;
		 }
		 return null;
	 }
	 
	 public String[] getCnt(){
		Cursor c = ourDatabase.rawQuery("SELECT * FROM memo", null);
         int count = c.getCount();
         if(count!=0){
         String[] values = new String[count + 1];
         int i = 0;
         for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        	   values[i] = c.getString(c.getColumnIndex("Name"));
        	}
		return values;
         }else{
        	 return null;
         }
        	 
	 } 
	
	 public void deleteEntry(int rowid) {
			// TODO Auto-generated method stub
			ourDatabase.delete(DATABASE_TABLE1, KEY_ID + "=" + rowid, null);
		}
	 
	 public int gettCount() {
		  String countQuery = "SELECT COUNT(*) FROM " + DATABASE_TABLE1;
		  if(ourDatabase!=null){
		  Cursor c = ourDatabase.rawQuery(countQuery, null);
		  c.moveToFirst();
		  int result=c.getCount();
		  c.close();
		  return result;
		  }else{
			  return 0;
		  }

		  // return count
		  
		 } 
	 public ArrayList<HashMap<String,String>>  getinfo() {
	        ArrayList<HashMap<String,String>> clist;
	        clist = new ArrayList<HashMap<String,String>>();
	        String query = "SELECT * FROM" + DATABASE_TABLE1;
	        Cursor c = ourDatabase.rawQuery(query,null);
	        if (c.moveToFirst())
	        {
	            do
	            {
	                HashMap<String,String> map=new HashMap<String,String>();
	                map.put("id1", c.getString(0));
	                map.put("image1", c.getString(1));
	                map.put("name1", c.getString(2));
	                map.put("description1", c.getString(3));
	                clist.add(map);
	            } while(c.moveToNext());
	        }
	        return clist;
	    }
}
