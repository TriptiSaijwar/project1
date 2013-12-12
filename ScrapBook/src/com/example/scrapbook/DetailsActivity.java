package com.example.scrapbook;

import java.io.ByteArrayOutputStream;






import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailsActivity extends Activity  {

	Button addImage,bAdd,bCan;
	EditText titletxt,discriptiontxt;
	ImageView scrapimg;
	
	 
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;
    
    byte[] imageName;
    Bitmap yourImage;
   
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
         setContentView(R.layout.details);
		
		    addImage = (Button) findViewById(R.id.picbutt);
		    bAdd = (Button) findViewById(R.id.btnSubmit);
	        bCan = (Button) findViewById(R.id.btnCancel);
	        titletxt = (EditText) findViewById(R.id.titletxt);
	        discriptiontxt = (EditText) findViewById(R.id.discriptiontxt);
	        scrapimg = (ImageView) findViewById(R.id.pic);
	        
			 final String[] option = new String[] { "Take from Camera",
		        "Select from Gallery" };
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        	    android.R.layout.select_dialog_item, option);
		        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setTitle("Select Option");
		       
		        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
		        	 public void onClick(DialogInterface dialog, int which) {
		        		    // TODO Auto-generated method stub
		        		   Log.e("Selected Item", String.valueOf(which));
		        		    if (which == 0) {
		        		     callCamera();
		        		    }
		        		    if (which == 1) {
		        		     callGallery();
		        		    }

		        		   } });
			final  AlertDialog dialog = builder.create();
			addImage.setOnClickListener(new View.OnClickListener() {
				   public void onClick(View v) {
				    dialog.show();
				   }
				  });
			
			 
	        bAdd.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					   String name1 = titletxt.getText().toString();
				       String dis1 = discriptiontxt.getText().toString();
				       if(name1!=null&&dis1!=null&&imageName!=null){
				    	   DataBaseHandler db = new DataBaseHandler(DetailsActivity.this);
							db.open();
							db.createEntry(imageName, name1, dis1);
							db.close();
						  Intent i = new Intent();
				                // send result code 100 to notify about product update
				               setResult(100, i);
				                finish(); 
				       }else{
				    	   (Toast.makeText(DetailsActivity.this, "Missing Entries", Toast.LENGTH_LONG)).show();
				       }
				
						
				}
			});
	         bCan.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				finish();
				}
			});        

				 }
		    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		 if (resultCode == RESULT_OK)
		 {

			  switch (requestCode) {
			  case CAMERA_REQUEST:

				   Bundle extras = data.getExtras();

				   if (extras != null) {
				     yourImage = extras.getParcelable("data");
				    // convert bitmap to byte
				     scrapimg.setImageBitmap(yourImage);
				    ByteArrayOutputStream stream = new ByteArrayOutputStream();
				    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
				     imageName = stream.toByteArray();
				    Log.e("output before conversion", imageName.toString());
				    // Inserting Contacts
				    Log.d("Insert: ", "Inserting ..");
				    (Toast.makeText(DetailsActivity.this, "Saved", Toast.LENGTH_LONG)).show();

				   }
				   break;
				  case PICK_FROM_GALLERY:
				   Bundle extras2 = data.getExtras();

				   if (extras2 != null) {
				    yourImage = extras2.getParcelable("data");
				    // convert bitmap to byte
				    scrapimg.setImageBitmap(yourImage);
				    ByteArrayOutputStream stream = new ByteArrayOutputStream();
				    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
				    imageName = stream.toByteArray();
				    Log.e("output before conversion", imageName.toString());
				    // Inserting Contacts
				    Log.d("Insert: ", "Inserting ..");
				   }

				   break;
				  }
				  
	}
		}
	
	public void callCamera() {
		  Intent cameraIntent = new Intent(
		    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		 /* cameraIntent.putExtra("crop", "true");
		  cameraIntent.putExtra("aspectX", 0);
		  cameraIntent.putExtra("aspectY", 0);
		  cameraIntent.putExtra("outputX", 200);
		  cameraIntent.putExtra("outputY", 150);*/
		  startActivityForResult(cameraIntent, CAMERA_REQUEST);}

   public void callGallery() {
	// TODO Auto-generated method stub
	Intent intent = new Intent();
	  intent.setType("image/*");
	  intent.setAction(Intent.ACTION_GET_CONTENT);
	 /* intent.putExtra("crop", "true");
	  intent.putExtra("aspectX", 0);
	  intent.putExtra("aspectY", 0);
	  intent.putExtra("outputX", 200);
	  intent.putExtra("outputY", 150);
	  intent.putExtra("return-data", true);*/
	  startActivityForResult(
	    Intent.createChooser(intent, "Complete action using"),
	    PICK_FROM_GALLERY);
}
	
	

}
