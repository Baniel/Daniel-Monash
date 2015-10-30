package com.example.assignment;

import java.io.InputStream;

import com.example.assignment.MovieDisplayActivity.BitmapUrl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDisplayActivity2 extends Activity {

	private Button displayButton;
	private TextView movieNameTextView;
	private ImageView movieImageView;

	private String movieUrl = "null";
	private String movieName = "null";
	private String movieDirector = "null";

	BitmapUrl bitmapUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.moviedisplay2_screen);

		displayButton = (Button) findViewById(R.id.button_dispalyPicturenewone);
		movieImageView = (ImageView) findViewById(R.id.imageView_moviedisplay);
		movieNameTextView = (TextView) findViewById(R.id.textView_movieName);

		Intent intent = getIntent();
		movieUrl = intent.getStringExtra("getMovieUrl");

		movieNameTextView.clearAnimation();

		movieName = intent.getStringExtra("getMovieName");
		movieNameTextView.setText(movieName);

		displayButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bitmapUrl = new BitmapUrl(movieImageView);
				bitmapUrl.execute(movieUrl);

				movieUrl = " ";

				Log.i("tag", movieUrl);
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
