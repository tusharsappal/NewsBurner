package com.tusharsappal.newsburner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends SherlockListActivity implements Filterable {

	private List<Message> messages = null;;
	int selection;

	ListAdapter mAdapter;
	ListAdapter originalListAdapter;
	List<String> titles = new ArrayList<String>();

	BaseFeedParser parser;
	NewsFilter newsFilter = new NewsFilter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// Adding the Action Bar in the Activity

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_second);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		// actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setCustomView(R.layout.text_view_action);

		final EditText search = (EditText) actionBar.getCustomView()
				.findViewById(R.id.txt_search);
		search.setText("");

		selection();

		PullData pulldata = new PullData();
		pulldata.execute();
		final ListAdapter listAdapter = new CustomListAdapter(
				SecondActivity.this, R.layout.custom_list, titles);

		mAdapter = listAdapter;
		originalListAdapter = listAdapter;

		this.setListAdapter(listAdapter);

		search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				((Filterable) listAdapter).getFilter().filter(s.toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		final ListView list_view = (ListView) findViewById(android.R.id.list);

		list_view.setAdapter(mAdapter);
		list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Message m = messages.get(arg2);
				Intent startDetailActivity = new Intent(SecondActivity.this,
						DetailsActivity.class);
				startDetailActivity.putExtra("message", m);
				startActivity(startDetailActivity);

			}

		});

	}

	private void selection() {

		String newspaperName = getIntent().getExtras().getString(
				"newspapername");

		if (newspaperName.equals("Hindustan Times")) {
			selection = 1;
		}
		else if (newspaperName.equals("The Times Of India")) {
			selection = 2;
		}
		else if (newspaperName.equals("NDTV News")) {
			selection = 4;
		} else if (newspaperName.equals("BBC News")) {
			selection = 5;
		} else if (newspaperName.equals("New York Times")) {
			selection = 6;
		}
		else if (newspaperName.equals("Yahoo India News"))
		{
			selection =7;
		}
	}

	public String trimDescription(String text_to_be_trimmed) {
		int index_of_char = text_to_be_trimmed.indexOf('<');
		String trimmed_string = text_to_be_trimmed.substring(0, index_of_char);
		return trimmed_string;

	}

	private class PullData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {

				if (selection == 1) {

					parser = new BaseFeedParser(
							"http://feeds.hindustantimes.com/HT-HomePage-TopStories"
							.toString());

				} else if (selection == 2) {
					parser = new BaseFeedParser(
							"http://timesofindia.feedsportal.com/c/33039/f/533965/index.rss"
							.toString());

				} else if (selection == 3) {
					parser = new BaseFeedParser(
							"http://feeds.feedburner.com/TechCrunch".toString());

				} else if (selection == 4) {
					parser = new BaseFeedParser(
							"http://feeds.feedburner.com/NdtvNews-TopStories"
							.toString());
				} else if (selection == 5) {
					parser = new BaseFeedParser(
							"http://feeds.bbci.co.uk/news/rss.xml".toString());
				} else if (selection == 6) {
					
					
					parser = new BaseFeedParser(
							"http://rss.nytimes.com/services/xml/rss/nyt/InternationalHome.xml"
							.toString());
				}
				else if (selection ==7)
				{
					parser = new BaseFeedParser("https://in.news.yahoo.com/rss/national".toString());
				}

				messages = parser.parse();
				for (Message msg : messages) {

					titles.add(msg.getTitle());

				}
			}

			catch (Exception e) {
				e.getMessage();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			((BaseAdapter) mAdapter).notifyDataSetChanged();
			return;
		}

	}

	@Override
	public Filter getFilter() {

		if (newsFilter == null) {
			NewsFilter newsFilter = new NewsFilter();
		}

		return newsFilter;
	}

	class NewsFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = titles;
				results.count = titles.size();
			} else {
				// We perform filtering operation
				List<String> nStringList = new ArrayList<String>();

				for (String s : titles) {
					if (s.startsWith(constraint.toString().toUpperCase()))
						nStringList.add(s);
				}

				results.values = nStringList;
				results.count = nStringList.size();

			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			if (results.count == 0) {
				((BaseAdapter) mAdapter).notifyDataSetInvalidated();
			} else {
				titles = ((List<String>) results.values);
				((BaseAdapter) mAdapter).notifyDataSetChanged();
			}

		}

	}

}
