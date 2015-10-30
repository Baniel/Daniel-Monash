package com.example.assignment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.example.assignment.R;
import com.example.assignment.HttpThread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDisplayActivity extends Activity {

	private TextView textView;
	private TextView directorTextView;
	private Context mContent;

	private ImageView imageView;

	private WebView webView;

	private Handler handler;

	private Button pictureButton;
	private Button selectCinemaButton;

	private String movieUrl = "null";
	private String movieName = "null";
	private String movieDirector = "null";

	BitmapUrl bitmapUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mContent = this;
		setContentView(R.layout.moviedisplay_screen);
		textView = (TextView) findViewById(R.id.textView12345);

		directorTextView = (TextView) findViewById(R.id.textView_director);

		imageView = (ImageView) findViewById(R.id.imageViewP);

		pictureButton = (Button) findViewById(R.id.pictureButton);
		selectCinemaButton = (Button) findViewById(R.id.button_selectCinema);

		Intent intent = getIntent();
		movieName = intent.getStringExtra("getMovieName");
		movieUrl = intent.getStringExtra("getMovieUrl");
		movieDirector = intent.getStringExtra("getMovieDirector");

		textView.setText(movieName);
		directorTextView.setText(movieDirector);

		Log.i("tag", " movieName的值为 " + movieName);

		Log.i("tag", "MovieUrl 为：" + movieUrl);

		pictureButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bitmapUrl = new BitmapUrl(imageView);
				bitmapUrl.execute(movieUrl);

			}
		});

		selectCinemaButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intentGo = new Intent(MovieDisplayActivity.this,
						CinemaActivity.class);
				intentGo.putExtra("MovieNameFromMovieDisplay", movieName);

				startActivity(intentGo);

			}
		});

	}

	public class BitmapUrl extends AsyncTask<String, Void, Bitmap> {

		private ImageView imageView;

		public BitmapUrl(ImageView imageview) {
			this.imageView = imageview;
		}

		protected Bitmap doInBackground(String... urls) {

			String url = urls[0];

			Bitmap bitmap = null;

			try {
				InputStream inputStream = new java.net.URL(url).openStream();
				bitmap = BitmapFactory.decodeStream(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return bitmap;
		}

		protected void onPostExecute(Bitmap result) {
			imageView.setImageBitmap(result);
		}

	}

}
