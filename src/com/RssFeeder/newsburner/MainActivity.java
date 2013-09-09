package com.RssFeeder.newsburner;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	
	Button b ;
	RadioButton radButton;
	RadioGroup radGroup;
	Intent intent;
	
	
	@Override
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
	
		b = (Button) findViewById(R.id.button1);
		radGroup = (RadioGroup) findViewById(R.id.newspaper);
		
		b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent  = new Intent(MainActivity.this,SecondActivity.class);
				
				int selectediD= radGroup.getCheckedRadioButtonId();
				radButton = (RadioButton) findViewById(selectediD);
				
			
				intent.putExtra("newspapername", radButton.getText().toString());
				startActivity(intent);
			
			//DownloadFeed feed = new DownloadFeed();
		//	feed.execute();
			}
			
			
			
		});
	
	
	}
	

} 
   
