package com.example.assignment;

import com.example.assignment.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

@SuppressWarnings("unused")
public class GalleryPicture extends Activity {

	private int[] res = { R.drawable.item1, R.drawable.item2, R.drawable.item3,
			R.drawable.item4, R.drawable.item5, R.drawable.item6, };

	private ImageAdapter adapter;
	@SuppressWarnings("deprecation")
	private android.widget.Gallery gallery;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_screen);

		gallery = (android.widget.Gallery) findViewById(R.id.gallery);

		adapter = new ImageAdapter(res, this);
		gallery.setAdapter(adapter);

	}
}
