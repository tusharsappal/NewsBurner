package com.tusharsappal.newsburner;


import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SecondActivity extends SherlockListActivity implements Filterable {

	private List<Message> messages = null;;
	int selection;
	int sub_selection;

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

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		} else {
			int newspaperName = extras.getInt("newspaper");
			int category = extras.getInt("subSelection");

			// sub_selection = getIntent().getExtras().getInt("subSelection");

			if (newspaperName == 0) {

				if (category == 0) {
					selection = 1;
					sub_selection = 1;
				}

				else if (category == 1) {
					selection = 1;
					sub_selection = 2;
				}

				else if (category == 2) {
					selection = 1;
					sub_selection = 3;
				}
			}

			else if (newspaperName == 1) {

				if (category == 0) {
					selection = 2;
					sub_selection = 1;
				} else if (category == 1) {
					selection = 2;
					sub_selection = 2;
				} else if (category == 2) {
					selection = 2;
					sub_selection = 3;
				}

			}

			else if (newspaperName == 2) {
				if (category == 0) {
					selection = 3;
					sub_selection = 1;
				} else if (category == 1) {
					selection = 3;
					sub_selection = 2;
				} else if (category == 2) {
					selection = 3;
					sub_selection = 3;
				}

			}

			else if (newspaperName == 3) {

				if (category == 0) {
					selection = 4;
					sub_selection = 1;
				} else if (category == 1) {
					selection = 4;
					sub_selection = 2;
				} else if (category == 2) {
					selection = 4;
					sub_selection = 3;
				}

			}

			else if (newspaperName == 4) {

				if (category == 0) {
					selection = 5;
					sub_selection = 1;
				} else if (category == 1) {
					selection = 5;
					sub_selection = 2;
				} else if (category == 2) {
					selection = 5;
					sub_selection = 3;
				}
			}

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

					if (sub_selection == 1) {
						parser = new BaseFeedParser(
								"http://feeds.hindustantimes.com/HT-HomePage-TopStories"
										.toString());
					} else if (sub_selection == 2) {
						parser = new BaseFeedParser(
								"http://feeds.hindustantimes.com/HT-Reviews"
										.toString());
					} else if (sub_selection == 3) {
						parser = new BaseFeedParser(
								"http://feeds.hindustantimes.com/HT-Business"
										.toString());
					}

				}

				else if (selection == 2) {

					if (sub_selection == 1) {
						parser = new BaseFeedParser(
								"http://timesofindia.feedsportal.com/c/33039/f/533965/index.rss"
										.toString());
					}

					else if (sub_selection == 2) {
						parser = new BaseFeedParser(
								"http://timesofindia.feedsportal.com/c/33039/f/533923/index.rss"
										.toString());
					} else if (sub_selection == 3) {
						parser = new BaseFeedParser(
								"http://timesofindia.feedsportal.com/c/33039/f/533919/index.rss"
										.toString());
					}

				}

				else if (selection == 3)

				{

					if (sub_selection == 1) {
						parser = new BaseFeedParser(
								"http://feeds.feedburner.com/NdtvNews-TopStories"
										.toString());
					}

					else if (sub_selection == 2) {
						parser = new BaseFeedParser(
								"http://feeds.feedburner.com/NDTV-Tech"
										.toString());
					}

					else if (sub_selection == 3) {
						parser = new BaseFeedParser(
								"http://feeds.feedburner.com/NDTV-Business"
										.toString());
					}

				}

				else if (selection == 4) {

					if (sub_selection == 1) {
						parser = new BaseFeedParser(
								"http://feeds.bbci.co.uk/news/rss.xml"
										.toString());
					}

					else if (sub_selection == 2) {
						parser = new BaseFeedParser(
								"http://feeds.bbci.co.uk/news/technology/rss.xml"
										.toString());
					} else if (sub_selection == 3) {
						parser = new BaseFeedParser(
								"http://feeds.bbci.co.uk/news/business/rss.xml"
										.toString());
					}

				}

				else if (selection == 5) {

					if (sub_selection == 1) {
						parser = new BaseFeedParser(
								"http://rss.nytimes.com/services/xml/rss/nyt/InternationalHome.xml"
										.toString());
					}

					else if (sub_selection == 2) {
						parser = new BaseFeedParser(
								"http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml"
										.toString());
					} else if (sub_selection == 3) {
						parser = new BaseFeedParser(
								"http://rss.nytimes.com/services/xml/rss/nyt/Business.xml"
										.toString());
					}

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
