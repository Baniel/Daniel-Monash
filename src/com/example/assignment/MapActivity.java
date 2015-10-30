package com.example.assignment;

import java.security.PublicKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mapquest.android.intent.MapQuestAppLauncher;
import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.DefaultItemizedOverlay;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.ItemizedOverlay;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.OverlayItem;
import com.mapquest.android.maps.PolygonOverlay;
import com.mapquest.android.maps.RouteResponse.Route.Shape;

import android.R.integer;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

public class MapActivity extends Activity {

	protected MapView map;
	AnnotationView annotation;

	private int height = 100;
	private int width = 100;
	private Drawable colorIcon;
	private Drawable movieIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(com.example.assignment.R.layout.map_screen);
		map = (MapView) findViewById(R.id.map);
		map.getController().setZoom(12);
		map.getController().setCenter(new GeoPoint(-37.876470, 145.044078));
		map.setBuiltInZoomControls(true);

		annotation = new AnnotationView(map);

		addPoioverlay();

	}

	private void addPoioverlay() {

		Drawable icon = getResources().getDrawable(R.drawable.location_marker);

		final DefaultItemizedOverlay poiOverlay = new DefaultItemizedOverlay(
				icon);

		OverlayItem myposition = new OverlayItem(new GeoPoint(-37.876470,
				145.044078), "You are here", "Near to Wufichart");
		poiOverlay.addItem(myposition);

		movieIcon = getResources().getDrawable(R.drawable.movie_maker);

		final DefaultItemizedOverlay poiMovie = new DefaultItemizedOverlay(
				movieIcon);

		OverlayItem movie1 = new OverlayItem(
				new GeoPoint(-37.830613, 145.05566), "Rivoli Cinema",
				"200 Camberwell Road Hawthorn East VIC 3123");
		poiMovie.addItem(movie1);

		OverlayItem movie2 = new OverlayItem(new GeoPoint(-37.797752,
				144.968244), "Cinema Nova", "380 Lygon Street Carlton VIC 3053");
		poiMovie.addItem(movie2);

		OverlayItem movie3 = new OverlayItem(new GeoPoint(-37.824197,
				144.957945), "Village Cinemas Crown",
				"50/8 Whiteman Street Southbank VIC 3006");
		poiMovie.addItem(movie3);

		OverlayItem movie4 = new OverlayItem(
				new GeoPoint(-37.817553, 144.96936), "ACMI",
				"Federation Square Flinders Street VIC 3000");
		poiMovie.addItem(movie4);

		OverlayItem movie5 = new OverlayItem(new GeoPoint(-37.814230,
				144.972021), "Kino Cinemas",
				"45 Collins Street Melbourne VIC 30000");
		poiMovie.addItem(movie5);

		OverlayItem movie6 = new OverlayItem(new GeoPoint(-37.884851,
				145.083915), "Chadstone Cinemas",
				"Chadstone Shopping Centre Middle Road Malvern East VIC 3145");
		poiMovie.addItem(movie6);

		colorIcon = getResources().getDrawable(R.drawable.color_icon);
		final DefaultItemizedOverlay poiColor = new DefaultItemizedOverlay(
				movieIcon);
		colorIcon.setBounds(-25, -25, 25, 25);

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.FILL);
		paint.setAlpha(3);
		PolygonOverlay polyOverlay = new PolygonOverlay(paint);

		poiOverlay.setTapListener(new ItemizedOverlay.OverlayTapListener() {

			@Override
			public void onTap(GeoPoint pt, MapView mapView) {

				int lastTouchedIndex = poiOverlay.getLastFocusedIndex();

				if (lastTouchedIndex > -1) {

					OverlayItem tapped = poiOverlay.getItem(lastTouchedIndex);

					annotation.showAnnotationView(tapped);

				}
			};

		});

		// add a tap listener for the Moive overlay

		poiMovie.setTapListener(new ItemizedOverlay.OverlayTapListener() {

			@Override
			public void onTap(GeoPoint pt, MapView mapView) {

				// when tapped,show the annotation for the overlayItem

				int lastTouchedIndexMovie = poiMovie.getLastFocusedIndex();

				int remberIcon = 0;

				if (lastTouchedIndexMovie > -1) {

					OverlayItem tappedItem = poiMovie
							.getItem(lastTouchedIndexMovie);

					Log.i("tag", String.valueOf(lastTouchedIndexMovie));
					// iconDrawable.setBounds(left, top, right, bottom)

					annotation.showAnnotationView(tappedItem);
					tappedItem.setMarker(colorIcon);
					// tappedItem.setMarker(colorIcon);

					map.invalidate();

				}
			};

		});

		poiColor.setTapListener(new ItemizedOverlay.OverlayTapListener() {

			@Override
			public void onTap(GeoPoint pt, MapView mapView) {

				// when tapped,show the annotation for the overlayItem

				int lastTouchedIndexColor = poiColor.getLastFocusedIndex();

				if (lastTouchedIndexColor > -1) {

					OverlayItem tappedItem = poiColor
							.getItem(lastTouchedIndexColor);

					tappedItem.setMarker(movieIcon);

					map.invalidate();

				}
			};

		});

		map.getOverlays().add(poiOverlay);
		map.getOverlays().add(poiMovie);
		map.getOverlays().add(poiColor);

	}

}
