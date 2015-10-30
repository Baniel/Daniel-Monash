package com.example.assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

public class HttpThread extends Thread {

	private String url;

	private WebView webView;

	private Handler handler;

	private ImageView imageView;

	public HttpThread(String url, ImageView imageView, Handler handler) {
		this.url = url;
		this.imageView = imageView;
		this.handler = handler;
	}

	@Override
	public void run() {

		try {
			URL httpUrl = new URL(url);
			try {

				HttpURLConnection connection = (HttpURLConnection) httpUrl
						.openConnection();
				connection.setReadTimeout(5000);
				connection.setRequestMethod("GET");

				connection.setDoInput(true);
				InputStream in = connection.getInputStream();
				FileOutputStream out = null;
				File downloadFile = null;

				String fileName = String.valueOf(System.currentTimeMillis());
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					File parent = Environment.getExternalStorageDirectory();
					downloadFile = new File(parent, fileName);
					out = new FileOutputStream(downloadFile);
				}
				byte[] b = new byte[2 * 1024];
				int len;
				if (out != null) {
					while ((len = in.read(b)) != -1) {
						out.write(b, 0, len);
					}
				}

				final Bitmap bitmap = BitmapFactory.decodeFile(downloadFile
						.getAbsolutePath());

				handler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						imageView.setImageBitmap(bitmap);
					}
				});

			} catch (IOException e) {
				// TODO: handle exception

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
