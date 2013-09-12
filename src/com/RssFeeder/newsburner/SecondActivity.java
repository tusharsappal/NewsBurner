package com.RssFeeder.newsburner;


import java.util.ArrayList;
import java.util.List;

import com.RssFeeder.newsburner.*;


import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;


public class SecondActivity extends ListActivity
{
	
	
	private List<Message> messages=null;;
	int selection;
	ArrayAdapter<String> mAdapter;
	List<String> titles= new ArrayList<String>();
	
	  BaseFeedParser parser;
 @Override
protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.activity_second);
	 
	
	
      
	  
	selection();
	
	PullData pulldata = new PullData();
	pulldata.execute();
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row,titles);
	mAdapter= adapter;
	
	 
	this.setListAdapter(adapter);
 
 
     // loadFeed();
  
 }
	
	
 
 private void selection ()
 {
	
	 

		String newspaperName= getIntent().getExtras().getString("newspapername");
		
		if(newspaperName.equals("The Hindu"))
		{
			
		selection =1;
		
		}
	 
		else if (newspaperName.equals("The Times Of India"))
		{
			
		
		selection =2;
		}
 }
 
 
 
 
 
 
 
 private class PullData extends AsyncTask<Void, Void, Void>
 {

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		try
		{
		  
	 		if(selection==1)
	 		{
	 			
	 		parser = new BaseFeedParser("http://feeds.washingtonpost.com/rss/politics".toString());
	 		
	 		//BaseFeedParser baseparser =null;
	 	    // parser.new DownloadData().execute();	
	 		
	 		}
	 		else if(selection ==2)
	 		{
	 			parser = new BaseFeedParser("http://feeds.washingtonpost.com/rss/politics".toString());
	 		
	 		//	BaseFeedParser baseparser =null;
	 		  //   parser.new DownloadData().execute();
	 		}
	 		
		    	messages = parser.parse();
		    	 titles = new ArrayList<String>(messages.size());
		    	for (Message msg : messages){
		    		titles.add(msg.getTitle());
		    	}
		}
		
		catch(Exception e)
		{
		 e.getMessage();
		}
		
		
		
		return null;
	}
	 
	@Override
	protected void onPostExecute(Void result) {
		
	 mAdapter.notifyDataSetChanged();
         return;
	}
 
 }
 
 
	

 
 
 
 
 

 
}
