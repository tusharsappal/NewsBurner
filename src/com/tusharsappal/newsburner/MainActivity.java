package com.tusharsappal.newsburner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.tusharsappal.newsburner.ExpandableListAdapter;
import com.actionbarsherlock.app.SherlockActivity;
//import com.google.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class MainActivity extends SherlockActivity {

	// private AdView adView;

	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> newsPaperCollection;
	ExpandableListView expListView;

	private Menu menu;

	// int sub_selection=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (isNetworkAvailable() == false) {
			Toast.makeText(
					getApplicationContext(),
					"Please Enable Your Internet Connection to View News Feeds",
					Toast.LENGTH_LONG).show();
		} else {

			createGroupList();

			createCollection();

			expListView = (ExpandableListView) findViewById(R.id.expList);
			final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
					this, groupList, newsPaperCollection);
			expListView.setAdapter(expListAdapter);

			// setGroupIndicatorToRight();

			expListView.setOnChildClickListener(new OnChildClickListener() {

				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					final String selected = (String) expListAdapter.getChild(
							groupPosition, childPosition);
					
					intent.putExtra("subSelection", childPosition);
					intent.putExtra("newspaper", groupPosition);
					intent.putExtra("subSelection", childPosition);

				

					startActivity(intent);
					return true;
				}
			});

			
		}

	}

	private void createGroupList() {

		// Hindustan Times",
		// "The Times Of India", "NDTV News", "BBC News", "New York Times",
		// "Yahoo India News
		groupList = new ArrayList<String>();
		groupList.add("Hindustan Times");
		groupList.add("The Times Of India");
		groupList.add("NDTV News");
		groupList.add("BBC News");
		groupList.add("New York Times");
		// groupList.add("Yahoo India News");
	}

	private void createCollection() {
		// preparing News Paper Subselection collection(child)

		String[] hindustanTimes = { "Top Stories", "Technology News",
				"Business News" };
		String[] theTimesOfIndia = { "Top Stories", "Technology News",
				"Business News" };
		String[] ndtvNews = { "Top Stories", "Technology News", "Business News" };
		String[] bbcNews = { "Top Stories", "Technology News", "Business News" };
		String[] newYorkTimes = { "Top Stories", "Technology News",
				"Business News" };

		newsPaperCollection = new LinkedHashMap<String, List<String>>();

		for (String newspapers : groupList) {
			if (newspapers.equals("Hindustan Times")) {
				loadChild(hindustanTimes);
			} else if (newspapers.equals("The Times Of India"))
				loadChild(theTimesOfIndia);
			else if (newspapers.equals("NDTV News"))
				loadChild(ndtvNews);
			else if (newspapers.equals("BBC News"))
				loadChild(bbcNews);
			else if (newspapers.equals("New York Times"))
				loadChild(newYorkTimes);

			newsPaperCollection.put(newspapers, childList);
		}
	}

	private void loadChild(String[] laptopModels) {
		childList = new ArrayList<String>();
		for (String model : laptopModels)
			childList.add(model);
	}

	private void setGroupIndicatorToRight() {
		/* Get the screen width */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;

		expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
				- getDipsFromPixel(5));
	}

	public int getDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}

	private boolean isNetworkAvailable() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

}
