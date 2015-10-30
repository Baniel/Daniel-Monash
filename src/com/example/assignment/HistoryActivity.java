package com.example.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HistoryActivity extends Activity {

	public SQLiteDatabase myDB;
	private String movieName;
	private String sessionTime;
	private String cinema;

	protected List<HashMap<String, String>> TaskListArray;
	protected SimpleAdapter myListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_screen);

		myDB = this.openOrCreateDatabase("Androiddatabase.db", MODE_PRIVATE,
				null);

		ListView mScreenLayout = (ListView) findViewById(R.id.listView1);
		TaskListArray = new ArrayList<HashMap<String, String>>();

		String[] colHEAD = new String[] { "MovieName", "SessionTime", "Cinema" };
		int[] dataCell = new int[] { R.id.textView_movieName,
				R.id.textView_sessionTime, R.id.textView_cinema };

		Cursor cursor = myDB.rawQuery("SELECT * FROM HISTORY", null);

		cursor.moveToFirst();

		if (cursor != null) {
			do {
				movieName = cursor.getString(cursor.getColumnIndex("MOVIE"));
				cinema = cursor.getString(cursor.getColumnIndex("CINEMA"));
				sessionTime = cursor.getString(cursor.getColumnIndex("TIME"));
				Log.i("tag", movieName + " " + cinema + " " + sessionTime);

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("MovieName", movieName);
				map.put("SessionTime", sessionTime);
				map.put("Cinema", cinema);

				TaskListArray.add(map);
			} while (cursor.moveToNext());
		}

		myListAdapter = new SimpleAdapter(this, TaskListArray,
				R.layout.list_view, colHEAD, dataCell);
		mScreenLayout.setAdapter(myListAdapter);

	}

}
