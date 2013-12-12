package com.example.scrapbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends Activity {
	
	TextView txt,vw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		txt = (TextView)findViewById(R.id.tvsht);
		vw  = (TextView)findViewById(R.id.tvshd);
		 Intent in = getIntent();
	        String ti = in.getStringExtra("keyti");
	        String di = in.getStringExtra("keydi");
	        
	        txt.setText(ti);
	        vw.setText(di);
	}
	

}
