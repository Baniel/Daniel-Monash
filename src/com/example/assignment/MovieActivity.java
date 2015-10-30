package com.example.assignment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.hardware.camera2.params.RggbChannelVector;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("NewApi") public class MovieActivity extends Activity implements OnCheckedChangeListener {

	GetRESTResponse task;
	private TextView textView;
	String err = " testing ";
	String resmsg = "";
	String jaxrsmessage = "";

	GetMovieUrl getMovieUrl;
	String err1 = "testing";
	String resmsg1 = "";
	String jaxrsmessage1 = "";
	
	GetMovieDirector getMovieDirector;
	String err2 = "testing";
	String resmsg2 = "";
	String jaxrsmessage2 = "";
	
	
	
	/*for search movie by input movie name*/
	GetUrl getUrl;
	String err3 = "testing";
	String resmsg3 = "";
	String jaxrsmessage3 = "";
	
	GetName getName;
	String err4 = "testing";
	String resmsg4 = "";
	String jaxrsmessage4 = "";
	

	private Context mContent;

	/* Initialize the AutoCompleteTextView */
	private AutoCompleteTextView acTextView;
	private String[] res = { "The Social Network", "The Dark Knight",
			"Star Wars", "Into the Woods", "Forrest Gump", "Inception" };

	/* Inititalize the Spinner */
	private List<String> list = new ArrayList<String>();
	private TextView myTextView;
	private Spinner mySpinner;
	private ArrayAdapter<String> adapter;

	/* Initialize the RadioGroup */
	private RadioGroup radioGroup;
	private RadioGroup radioGroupRating;

	/* Initialize the RatingBar */
	private RatingBar ratingBar;

	/* Initialize the Button */
	private Button searchButton;

	/* Initialize the searchInformation */
	private String selectedInformation;

	/* input value */
	private String genre;
	private String rating;
	private String star;
	private String director;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
	
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_screen);

		mContent = this;

		acTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, res);
		acTextView.setAdapter(adapter);
	

		list.add("David Fincher");
		list.add("Christopher Nolan");
		list.add("George Lucas");
		list.add("Rob Marshall");
		list.add("Robert Zemeckis");
		list.add("Christopher Nolan");

		mySpinner = (Spinner) findViewById(R.id.spinner_director);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mySpinner.setAdapter(adapter);

		mySpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

						switch (arg2) {
						case 0:
							Log.i("tag", "David Fincher");
							director = "David Fincher";

							break;
						case 1:
							Log.i("tag", "Christopher Nolan");
							director = "Christopher Nolan";
							break;

						case 2:
							Log.i("tag", "George Lucas");
							director = "George Lucas";
							break;

						case 3:
							Log.i("tag", "Rob Marshall");
							director = "Rob Marshall";
							break;
						case 4:
							Log.i("tag", "Robert Zemeckis");
							director = "Robert Zemeckis";
							break;
						case 5:
							Log.i("tag", "Christopher Nolan");
							director = "Christopher Nolan";
							break;
						default:
							break;
						}

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						myTextView.setText("NONE");
						arg0.setVisibility(View.VISIBLE);
					}
				});

		mySpinner.setOnTouchListener(new Spinner.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
		mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {

			}
		});

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		radioGroupRating = (RadioGroup) findViewById(R.id.radioGroup2);

		/*
		 * Come true the raioGroup Listenster
		 */

		radioGroup.setOnCheckedChangeListener(this);
		radioGroupRating.setOnCheckedChangeListener(this);

		/* Create Rating bar Listener */
			ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
			ratingBar
					.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
	
						@Override
						public void onRatingChanged(RatingBar ratingBar,
								float rating, boolean fromUser) {
	
							star = String.valueOf(rating);
						
							Log.i("tag", star);
	
						}
					});

		/*
		 * Come true the radioGroup Listenster
		 */

		/*
		 * Come true the Button Listener
		 */
		searchButton = (Button) findViewById(R.id.button1);

		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				
				if(!acTextView.getText().toString().trim().isEmpty())
				{
				
					String movieNameString = "";
					String movieUrlString ="";
				
					
					
					getUrl = new GetUrl();
					getUrl.execute();
					
					try {
						movieUrlString = getUrl.get();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					
					getName = new GetName();
					getName.execute();
					try {
						movieNameString = getName.get();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					
				 	     Intent intent = new Intent(MovieActivity.this,MovieDisplayActivity2.class);
					     intent.putExtra("getMovieUrl", movieUrlString);
					     intent.putExtra("getMovieName", movieNameString);
					  
					  startActivity(intent);
					
				}else{
				
				String movieName = "";
				String movieUrl = "";
				String movieDirector = "";
				
				Log.i("tag",genre+" " + rating + " " + star + " " + director);
				
				
				task = new GetRESTResponse();
			    task.execute();
			    try {
					movieName = task.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			    
			    getMovieUrl = new GetMovieUrl();
			    getMovieUrl.execute();
			    try {
					movieUrl = getMovieUrl.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			    
			    Log.i("tag","第一页movieName的值：" + movieName);
				  Log.i("tag","第一页movieUrl的值： " + movieUrl);
			    
			    
				  getMovieDirector = new GetMovieDirector();
				  getMovieDirector.execute();
				  try {
					movieDirector = getMovieDirector.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			    
				  
			    Intent intent = new Intent(MovieActivity.this,MovieDisplayActivity.class);
			     intent.putExtra("getMovieName", movieName);
			     intent.putExtra("getMovieUrl", movieUrl);
			     intent.putExtra("getMovieDirector", movieDirector);
			  
			  startActivity(intent);
			  
			}
			  
			}
		});

	}

	
	/* The method for RadioGroup Listener */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch (checkedId) {
		case R.id.radio0:
			Log.i("tag", "Drama");
			genre = "DRAMA";
			break;
		case R.id.radio1:
			Log.i("tag", "Action");
			genre = "ACTION";

			break;
		case R.id.radio2:
			Log.i("tag", "Sci-Fi");
			genre = "SCI-FI";

			break;
		case R.id.radio3:
			Log.i("tag", "Fantasy");
			genre = "FANTASY";
			break;

		case R.id.radio01:
			Log.i("tag", "M");
			rating = "M";
			break;

		case R.id.radi02:
			Log.i("tag", "PG");
			rating = "PG";
			break;

		}

	}

	/* Get the information form user input */
	public String getSelectedInformation() {
		Log.i("tag", selectedInformation);

		return selectedInformation;

	}

	
	
	/* AsyncTask translate the date */

	private class GetRESTResponse extends AsyncTask<String, Void, String> {
		
		
		@Override
		protected String doInBackground(String... args) { // Making HTTP request
			
			
			Log.i("tag","you are in");
			
			
			try {
				// defaultHttpClient
				HttpClient httpclient = new DefaultHttpClient();
				
				
				String url ="http://192.168.1.6:8080/coolStyle/webresources/com.database.movie/MovieName/"
						+ genre
						+ "/"
						+ rating
						+ "/"
						+ star
						+ "/"
						+ director;		
				
			  url = url.replaceAll(" ", "%20");	
				
			 
				
				HttpGet request = new HttpGet(url);		
			
				 Log.i("tag","url:" + url);
				
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
					Log.i("tag","Result :" + result);
				
			
		}
		
		
		
	}
	
	
	/*Get Url*/
	
private class  GetMovieUrl extends AsyncTask<String, Void, String> {
		
		
		@Override
		protected String doInBackground(String... args) { // Making HTTP request
			
			
			Log.i("tag","you are in");
			
			
			try {
				// defaultHttpClient
				HttpClient httpclient = new DefaultHttpClient();
				
				
				String url ="http://192.168.1.6:8080/coolStyle/webresources/com.database.movie/MovieUrl/"
						+ genre
						+ "/"
						+ rating
						+ "/"
						+ star
						+ "/"
						+ director;		
				
			  url = url.replaceAll(" ", "%20");	
				
			 
				
				HttpGet request = new HttpGet(url);		
			
				 Log.i("tag","url:" + url);
				
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
					Log.i("tag","Result :" + result);
			
		}
		
		
	}
	
	
	
	
	
/*Get director*/
private class  GetMovieDirector extends AsyncTask<String, Void, String> {



@Override
protected String doInBackground(String... args) { // Making HTTP request
	
	
	Log.i("tag","you are in");
	
	
	try {
		// defaultHttpClient
		HttpClient httpclient = new DefaultHttpClient();
		
		
		String url ="http://192.168.1.6:8080/coolStyle/webresources/com.database.movie/MovieDirector/"
				+ genre
				+ "/"
				+ rating
				+ "/"
				+ star
				+ "/"
				+ director;		
		
	  url = url.replaceAll(" ", "%20");	
		
	 
		
		HttpGet request = new HttpGet(url);		
	
		 Log.i("tag","url:" + url);
		
		// here use your own REST url and IP address
		HttpResponse response = httpclient.execute(request);
		if (response == null)
			err2 = "resp error";
		Log.i("after resp", err2);
		HttpEntity entity = response.getEntity();
		InputStream instream = entity.getContent();
		BufferedReader buffer = new BufferedReader(
				new InputStreamReader(instream));
		String s = "";
		while ((s = buffer.readLine()) != null) {
			resmsg2 += s;
		}
		Log.i("Respones is ", resmsg2);
	} catch (Exception e) {
		e.printStackTrace();
	}
	Log.i("tag", "hi I am end in");
	return resmsg2;
}

@Override
protected void onPostExecute(final String result) {
			
			Log.i("tag", "I am coming");
			Log.i("tag","Result :" + result);
	
}

}
	
	
	

/*Get movie Url by input movie Name*/
private class  GetUrl extends AsyncTask<String, Void, String> {



@Override
protected String doInBackground(String... args) { // Making HTTP request
	
	
	Log.i("tag","you are in");
	
	
	try {
		// defaultHttpClient
		HttpClient httpclient = new DefaultHttpClient();
		
		
		String url ="http://192.168.1.6:8080/coolStyle/webresources/com.database.movie/Url/"
				+ acTextView.getText().toString().toUpperCase();
				
		
	  url = url.replaceAll(" ", "%20");	
		
	 
		
		HttpGet request = new HttpGet(url);		
	
		 Log.i("tag","url:" + url);
		
		// here use your own REST url and IP address
		HttpResponse response = httpclient.execute(request);
		if (response == null)
			err3 = "resp error";
		Log.i("after resp", err2);
		HttpEntity entity = response.getEntity();
		InputStream instream = entity.getContent();
		BufferedReader buffer = new BufferedReader(
				new InputStreamReader(instream));
		String s = "";
		while ((s = buffer.readLine()) != null) {
			resmsg3 += s;
		}
		Log.i("Respones is ", resmsg3);
	} catch (Exception e) {
		e.printStackTrace();
	}
	Log.i("tag", "hi I am end in");
	return resmsg3;
}

@Override
protected void onPostExecute(final String result) {
	
	
			
			Log.i("tag", "I am coming");
			Log.i("tag","Result :" + result);
			
	
}

}
	
/*Get movie name by input movie Name*/

private class  GetName extends AsyncTask<String, Void, String> {





@Override
protected String doInBackground(String... args) { // Making HTTP request
	
	
	Log.i("tag","you are in");
	
	
	try {
		// defaultHttpClient
		HttpClient httpclient = new DefaultHttpClient();
		
		
		String url ="http://192.168.1.6:8080/coolStyle/webresources/com.database.movie/Name/"
				+ acTextView.getText().toString().toUpperCase();
				
	  url = url.replaceAll(" ", "%20");	
		
	 
		
		HttpGet request = new HttpGet(url);		
	
		 Log.i("tag","url:" + url);
		
		// here use your own REST url and IP address
		HttpResponse response = httpclient.execute(request);
		if (response == null)
			err4 = "resp error";
		Log.i("after resp", err4);
		HttpEntity entity = response.getEntity();
		InputStream instream = entity.getContent();
		BufferedReader buffer = new BufferedReader(
				new InputStreamReader(instream));
		String s = "";
		while ((s = buffer.readLine()) != null) {
			resmsg4 += s;
		}
		Log.i("Respones is ", resmsg4);
	} catch (Exception e) {
		e.printStackTrace();
	}
	Log.i("tag", "hi I am end in");
	return resmsg4;
}

@Override
protected void onPostExecute(final String result) {
	
			
			Log.i("tag", "I am coming");
			Log.i("tag","Result :" + result);
		
			
	
}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
