package com.markupartist.android.example.pulltorefresh;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

public class PullToRefreshActivity extends Activity {
	private LinkedList<String> mListItems;
	public static PullToRefreshListView weiboListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull_to_refresh);
		weiboListView = (PullToRefreshListView) findViewById(R.id.weibolist);

		// Set a listener to be invoked when the list should be refreshed.
		weiboListView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Do work to refresh the list here.
				new GetDataTask(PullToRefreshActivity.this, 0).execute();
			}

			@Override
			public void onLoadMore() {
				new GetDataTask(PullToRefreshActivity.this, 1).execute();
			}
		});

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mListItems);

		weiboListView.setAdapter(adapter);
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {
		private Context context;
		private int index;

		public GetDataTask(Context context, int index) {
			this.context = context;
			this.index = index;
		}

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				;
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			if (index == 0) {
				// ���ַ�����Added after refresh����ӵ�����
				mListItems.addFirst("Added after refresh...");

				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy��MM��dd��  HH:mm");
				String date = format.format(new Date());
				// Call onRefreshComplete when the list has been refreshed.
				weiboListView.onRefreshComplete(date);
			} else if (index == 1) {
				mListItems.addLast("Added after loadmore...");
				weiboListView.onLoadMoreComplete();
			}

			super.onPostExecute(result);
		}
	}

	public static String[] mStrings = { "һ��΢��", "����΢��", "����΢��", "����΢��", "����΢��",
			"����΢��", "����΢��", "����΢��", "����΢��", "ʮ��΢��", "ʮһ��΢��", "ʮ����΢��" };

}
