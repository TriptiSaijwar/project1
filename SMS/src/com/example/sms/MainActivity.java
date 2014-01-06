package com.example.sms;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	EditText  txtPassword;
	SharedPreferences pref;
	Button btnLogin,chngKey;
	SessionManagement session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 session = new SessionManagement(getApplicationContext());
		
		txtPassword = (EditText) findViewById(R.id.etpass1);
		btnLogin = (Button) findViewById(R.id.btlogin1);
		chngKey = (Button) findViewById(R.id.btchnge1);
		chngKey.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btlogin1:
		{
		String keyword = txtPassword.getText().toString();
		
		if(keyword.length()>0){
			if(keyword.equals("test")){
				session.createKey(keyword);
				Intent i = new Intent(MainActivity.this,ChangeProfile.class);
				 startActivity(i);
			}
		}
		}
		break;
		
		case R.id.btchnge1:
		{
			session.changekey();
			 Intent i = new Intent(getApplicationContext(), MainActivity.class);
             startActivity(i);
             finish();
		}
		
		
		}
	}
}
