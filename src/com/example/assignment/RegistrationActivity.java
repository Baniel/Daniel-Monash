package com.example.assignment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MyLocationOverlay;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends Activity {

	ContentValues values;// For database

	private Context mContent;// For Intent

	/* Initialize the Database */
	public SQLiteDatabase myDB;

	/* Initialize the User Name TextView */
	private EditText userNameEditText;

	/* Initialize the Password TextView */
	private EditText passwordEditText;

	/* Initialize the ImageButton */
	private ImageButton registrationImageButton;

	/* Initialize the Calender to get the date */
	private Calendar calendar;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String dateString;

	/* Initialize the Llocation data */
	private LocationManager locationManager;
	private double latitude = 0.0;
	private double longitude = 0.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);

		myDB = this.openOrCreateDatabase("Androiddatabase.db", MODE_PRIVATE,
				null);

		mContent = this;

		initDatabase(); // Create the Database

		// Get Instance
		userNameEditText = (EditText) findViewById(R.id.editText1);
		passwordEditText = (EditText) findViewById(R.id.editText2);
		registrationImageButton = (ImageButton) findViewById(R.id.imageButton1);

		// Get a Instance of calendar

		calendar = Calendar.getInstance();
		// Get the time
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);

		setTitle(year + "-" + month + "-" + day + "-" + hour + "-" + minute);
		dateString = String.valueOf(year + "-" + month + "-" + day);

		// Get the location
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				latitude = location.getLatitude();
				longitude = location.getLatitude();
			} else {
				latitude = -37.876470;
				longitude = 145.044078;
			}

		}

		// Add the Listener for the ImageButton
		registrationImageButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				String userName = userNameEditText.getText().toString();
				String password = passwordEditText.getText().toString();
				String encodePasswod = " ";

				if (!password.trim().equals("")) {
					encodePasswod = Base64.encodeToString(password.getBytes(),
							Base64.DEFAULT);

				}

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					Log.i("tag", userName);
					Log.i("tag", password);
					Log.i("tag", encodePasswod);
					Log.i("tag", dateString);

				} else if (event.getAction() == MotionEvent.ACTION_UP) {

					openDatabase();

					if (userName.trim().equals("")
							&& !password.trim().equals("")) {
						showToast2();

					}

					if (password.trim().equals("")
							&& !userName.trim().equals("")) {
						showToast3();
					}

					if (userName.trim().equals("")
							&& password.trim().equals("")) {
						Log.i("tag", "Please,input a UserName and Password");

						showToast1();

					}

					if (!userName.trim().equals("")
							&& !password.trim().equals("")) {

						openDatabase();

						values = new ContentValues();
						values.put("USER_NAME", userName);
						values.put("PASSWORD", encodePasswod);
						values.put("DATE", dateString);
						values.put("LATITUDE", latitude);
						values.put("LONGITUDE", longitude);

						long rowId = myDB.insert("USER_TABLE", null, values);

						myDB.close();

						Intent intent = new Intent(mContent,
								LoginActivity.class);
						startActivity(intent);

					}

				}
				return false;
			}
		});

	}

	/* Create a toast when user name and password is null */

	private void showToast1() {
		Toast toast = Toast.makeText(this,
				"Please,input User Name and Password", Toast.LENGTH_SHORT);
		toast.show();
	}

	private void showToast2() {
		Toast toast = Toast.makeText(this, "Please,input User Name ",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	private void showToast3() {
		Toast toast = Toast.makeText(this, "Please,input Password ",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	private void showToast4() {
		Toast toast = Toast.makeText(this,
				"User name has existed,Please,change the user name!!! ",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	/*
	 * Create database method
	 */
	public void initDatabase() {

		myDB = this.openOrCreateDatabase("Androiddatabase.db", MODE_PRIVATE,
				null);

		myDB.execSQL("PRAGMA foreign_keys=ON;");

		try {

			myDB.execSQL("CREATE TABLE IF NOT EXISTS USER_TABLE(USER_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,USER_NAME VARCHAR(50) NOT NULL,PASSWORD VARCHAR(50) NOT NULL,DATE VARCHAR(50) NOT NULL,LATITUDE DOUBLE NOT NULL,LONGITUDE DOUBLE NOT NULL);");

			myDB.execSQL("CREATE TABLE IF NOT EXISTS HISTORY(HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,CINEMA VARCHAR(50) NOT NULL,MOVIE VARCHAR(50) NOT NULL,TIME VARCHAR(50) NOT NULL,USER_ID INTEGER(5),FOREIGN KEY(USER_ID) REFERENCES USER_TABLE (USER_ID));");

		} catch (Exception e) {
			Log.e("Error", "Error", e);
		} finally {
			if (myDB != null)
				myDB.close();
		}

	}

	/*
	 * Create or open a method to open database
	 */

	public void openDatabase() {
		myDB = this.openOrCreateDatabase("Androiddatabase.db", MODE_PRIVATE,
				null);
	}

	/*
	 * Read name from the database
	 */

	public boolean checkUserName(String USER_NAME) {
		openDatabase();
		Cursor c = myDB.rawQuery(
				"select * from USER_TABLE where USER_NAME = ?",
				new String[] { USER_NAME });
		if (c.moveToFirst()) {

			return false;
		} else {

			return true;

		}

	}

}
