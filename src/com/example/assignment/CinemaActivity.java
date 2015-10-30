package com.example.assignment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.menu;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CinemaActivity extends Activity {

	public SQLiteDatabase myDB;

	/* Initialize the vlaue of AsynTalk */

	GetCinema getCinema;
	String err = " testing ";
	String resmsg = "";
	String jaxrsmessage = "";

	GetData getData;
	String err1 = " testing ";
	String resmsg1 = "";
	String jaxrsmessage1 = "";

	private String selectedCinema;
	private String selectedTime;

	private String cinemaName = "";
	private String sesstionTime = "";
	private String sesstionDate = "";

	private String movieName = "";
	private TextView movieNameTextView;
	protected TextView selectedTextView;

	private RadioGroup cinemaRadioGroup;
	private RadioButton cinema1RadioButton;
	private RadioButton cinema2RadioButton;

	private Button data1Button;
	private Button data2Button;
	private Button data3Button;
	private Button data4Button;
	protected Button selectedButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cinema_screen);

		myDB = this.openOrCreateDatabase("Androiddatabase.db", MODE_PRIVATE,
				null);

		movieNameTextView = (TextView) findViewById(R.id.textView_movieName);
		cinemaRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_cinema);
		cinema1RadioButton = (RadioButton) findViewById(R.id.radio_cinema1);
		cinema2RadioButton = (RadioButton) findViewById(R.id.radio_cinema2);

		data1Button = (Button) findViewById(R.id.button_data1);
		data2Button = (Button) findViewById(R.id.button_data2);
		data3Button = (Button) findViewById(R.id.button_data3);
		selectedButton = (Button) findViewById(R.id.button_selectednew);

		registerForContextMenu(findViewById(R.id.button_selectednew));

		Intent intent = getIntent();
		movieName = intent.getStringExtra("MovieNameFromMovieDisplay");

		movieNameTextView.setText(movieName);

		getCinema = new GetCinema();
		getCinema.execute();

		try {
			cinemaName = getCinema.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] cinemaArray = new String[] {};

		cinemaArray = cinemaName.split(",");

		cinema1RadioButton.setText(cinemaArray[0]);
		cinema2RadioButton.setText(cinemaArray[1]);

		final String cinema1 = cinemaArray[0];
		final String cinema2 = cinemaArray[1];

		getData = new GetData();
		getData.execute();
		try {
			sesstionDate = getData.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] sessionTimeArray = new String[] {};
		sessionTimeArray = sesstionDate.split(",");
		final String time1 = sessionTimeArray[0];
		final String time2 = sessionTimeArray[1];
		final String time3 = sessionTimeArray[2];

		data1Button.setText(sessionTimeArray[0]);
		data2Button.setText(sessionTimeArray[1]);
		data3Button.setText(sessionTimeArray[2]);
		// data4Button.setText(sessionTimeArray[3]);

		/* Create a listener for cinema Radio Group */

		cinemaRadioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub

						switch (checkedId) {
						case R.id.radio_cinema1:
							selectedCinema = cinema1;
							break;

						case R.id.radio_cinema2:
							selectedCinema = cinema2;

						}

					}
				});

		/* Selected Button Listener */
		selectedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			    alert();	
			
				registerForContextMenu(findViewById(R.id.button_selectednew));

				ContentValues values = new ContentValues();

				values.put("CINEMA", selectedCinema);
				values.put("MOVIE", movieName);
				values.put("TIME", selectedTime);
				values.put("USER_ID", 2);

				myDB.insert("HISTORY", null, values);
				myDB.close();

			}
		});

		data1Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedTime = time1;

			}
		});

		data2Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedTime = time2;
			}
		});

		data3Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				selectedTime = time3;
			}
		});

	}

	
	
	public void alert()
	{
		new AlertDialog.Builder(this).setTitle("Are you sure").setMessage("Want to see this move").setPositiveButton("Yeah,I likt it", null).setNegativeButton("No,Let me Think againg", null).show();

	}

	/* Get the Cinema */

	private class GetCinema extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... args) { // Making HTTP request

			Log.i("tag", "you are in");

			try {
				// defaultHttpClient
				HttpClient httpclient = new DefaultHttpClient();

				String url = "http://192.168.1.6:8080/coolStyle/webresources/com.database.session/Cinema/"
						+ movieName.toUpperCase();

				Log.i("tag", movieName);

				url = url.replaceAll(" ", "%20");

				HttpGet request = new HttpGet(url);

				Log.i("tag", "url:" + url);

				// here use your own REST url and IP address
				HttpResponse response = httpclient.execute(request);
				if (response == null)
					err = "resp error";
				Log.i("after resp", err);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					resmsg += s;
				}
				Log.i("Respones is ", resmsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.i("tag", "hi I am end in");
			return resmsg;
		}

		@Override
		protected void onPostExecute(final String result) {

			Log.i("tag", "I am coming");
			Log.i("tag", "movieName :" + result);

		}

	}

	/* Get the Data */

	private class GetData extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... args) { // Making HTTP request

			Log.i("tag", "you are in");

			try {
				// defaultHttpClient
				HttpClient httpclient = new DefaultHttpClient();

				String url = "http://192.168.1.6:8080/coolStyle/webresources/com.database.session/Date/"
						+ movieName.toUpperCase();

				Log.i("tag", movieName);

				url = url.replaceAll(" ", "%20");

				HttpGet request = new HttpGet(url);

				Log.i("tag", "url:" + url);

				// here use your own REST url and IP address
				HttpResponse response = httpclient.execute(request);
				if (response == null)
					err = "resp error";
				Log.i("after resp", err1);
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(instream));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					resmsg1 += s;
				}
				Log.i("Respones is ", resmsg1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.i("tag", "hi I am end in");
			return resmsg1;
		}

		@Override
		protected void onPostExecute(final String result) {

			Log.i("tag", "I am coming");
			Log.i("tag", "movieName :" + result);

		}

	}

}
