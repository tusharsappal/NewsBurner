package com.tusharsappal.newsburner;

import com.actionbarsherlock.app.SherlockListActivity;
//import com.google.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
import com.tusharsappal.newsburner.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends SherlockListActivity {

	Intent intent;
	// private AdView adView;

	String[] news_papers = new String[] { "Hindustan Times",
			"The Times Of India", "NDTV News", "BBC News", "New York Times",
			"Yahoo India News" };

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

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, news_papers);

			setListAdapter(adapter);
		}

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Intent intent = new Intent(MainActivity.this, SecondActivity.class);

		switch (position) {
		case 0:
			intent.putExtra("newspapername", news_papers[0]);
			break;
		case 1:

			intent.putExtra("newspapername", news_papers[1]);
			break;

		case 2:
			intent.putExtra("newspapername", news_papers[2]);
			break;
		case 3:
			intent.putExtra("newspapername", news_papers[3]);
			break;
		case 4:
			intent.putExtra("newspapername", news_papers[4]);
			break;
		case 5:
			intent.putExtra("newspapername", news_papers[5]);

		default:
			break;
		}

		startActivity(intent);

		super.onListItemClick(l, v, position, id);
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
