package com.example.assignment;

import javax.security.auth.PrivateCredentialPermission;

import com.example.slidingmenu.view.SlidingMenu;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.sip.SipAudioCall.Listener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.SimpleAdapter;

public class MainActivity extends ActionBarActivity {

	private SlidingMenu mLeftMenu;

	// Variable to change the Screen
	private Context mContent;
	private Button registrationButton;
	private Button movieButton;
	private Button cinemaButton;
	private Button sessionButton;
	private Button historyButton;
	private Button mapButton;
	private Button moreMovieButton;

	// Gallery

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);

		mContent = this;
		registrationButton = (Button) findViewById(R.id.registration_button);
		movieButton = (Button) findViewById(R.id.movie_button);
		cinemaButton = (Button) findViewById(R.id.cinema_button);
		sessionButton = (Button) findViewById(R.id.session_button);
		historyButton = (Button) findViewById(R.id.history_button);
		mapButton = (Button) findViewById(R.id.map_button);
		moreMovieButton = (Button) findViewById(R.id.moreMoive_button);

		/* Listener for registration button */
		registrationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContent, RegistrationActivity.class);
				startActivity(intent);
			}
		});

		// Listener for movie
		movieButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContent, MovieActivity.class);
				startActivity(intent);
			}
		});

		// Listener for cinema

		cinemaButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContent, CinemaActivity.class);
				startActivity(intent);
			}
		});

		// Listene for session

		sessionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContent, SessionActivity.class);
				startActivity(intent);
			}
		});

		// Listen for history
		historyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContent, HistoryActivity.class);
				startActivity(intent);
			}
		});

		// Listener for map
		mapButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContent, MapActivity.class);
				startActivity(intent);
			}
		});

		// Listener for More Movie Button
		moreMovieButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContent, GalleryPicture.class);
				startActivity(intent);

			}
		});

	}

	public void toggleMenu(View view) {
		mLeftMenu.toggle();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
