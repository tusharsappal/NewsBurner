package com.tusharsappal.newsburner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	Message messageReceivedFromIntent;

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);

		setContentView(R.layout.detail_view);

		Intent i = getIntent();
		messageReceivedFromIntent = (Message) i.getSerializableExtra("message");

		TextView text_title = (TextView) findViewById(R.id.TextView_title_details);
		text_title.setText(messageReceivedFromIntent.getTitle());

		TextView text_description = (TextView) findViewById(R.id.TextView_description_details);
		text_description.setText(trimDescription(messageReceivedFromIntent
				.getDescription()));

	}

	public String trimDescription(String text_to_be_trimmed) {
		int index_of_char = text_to_be_trimmed.indexOf('<');
		if (index_of_char != -1) {
			String trimmed_string = text_to_be_trimmed.substring(0,
					index_of_char);
			return trimmed_string;
		} else {
			return text_to_be_trimmed;
		}

	}

}
