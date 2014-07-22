package com.tusharsappal.newsburner;

import java.util.List;
import java.util.Map;

import com.tusharsappal.newsburner.R;

import android.app.Activity;

import android.content.Context;

import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Activity context;
	private Map<String, List<String>> newsPaperCollection;
	private List<String> newspapers;

	public ExpandableListAdapter(Activity context, List<String> laptops,
			Map<String, List<String>> laptopCollections) {
		this.context = context;
		this.newsPaperCollection = laptopCollections;
		this.newspapers = laptops;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {

		return newsPaperCollection.get(newspapers.get(groupPosition)).get(
				childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String laptop = (String) getChild(groupPosition, childPosition);
		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.child_item, null);
		}

		TextView item = (TextView) convertView.findViewById(R.id.subSelection);

		item.setText(laptop);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return newsPaperCollection.get(newspapers.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return newspapers.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return newspapers.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		String laptopName = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.group_item, null);
		}
		TextView item = (TextView) convertView
				.findViewById(R.id.newspapersGroup);
		item.setTypeface(null, Typeface.BOLD);
		item.setText(laptopName);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {

		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
