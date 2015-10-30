package com.example.assignment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {

	private int[] res;
	private Context context;

	public ImageAdapter(int[] res, Context context) {
		this.res = res;
		this.context = context;
	}

	// back to the number of resource
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return res.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return res[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("Main", "position=" + position + "res的角标" + position % res.length);
		ImageView image = new ImageView(context);
		image.setBackgroundResource(res[position]);

		image.setScaleType(ScaleType.FIT_XY);
		return image;
	}

}
