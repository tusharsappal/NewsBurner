package com.tusharsappal.newsburner;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter {

	private Context mContext;
	private int id;
	private List<String> items;
	private String[] newspapers;

	@SuppressWarnings("unchecked")
	public CustomListAdapter(Context context, int textViewResourceId,
			List<String> list) {
		super(context, textViewResourceId, list);
		mContext = context;
		id = textViewResourceId;
		items = list;
	}

	@SuppressWarnings("unchecked")
	public CustomListAdapter(MainActivity context, int customList,
			String[] news_papers) {
		super(context, customList, news_papers);
		mContext = context;
		id = customList;
		newspapers = news_papers;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View mView = v;
		if (mView == null) {
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mView = vi.inflate(id, null);
		}

		TextView text = (TextView) mView.findViewById(R.id.textView);

		if (items.get(position) != null) {
			text.setTextColor(Color.BLACK);
			text.setText(items.get(position));
			// text.setBackgroundColor(Color.WHITE);
			text.setTypeface(Typeface.DEFAULT_BOLD);
			// int color = Color.argb( 200, 255, 64, 64 );
			// text.setBackgroundColor( color );

		}

		return mView;
	}

}
