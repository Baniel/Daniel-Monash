package com.example.assignment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LoginActivity extends Activity {

	private Context mContent;
	private Button loginButton;
	private Button registrationButton;
	private EditText userNameEditText;
	private EditText passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login_screen);
		mContent = this;
		loginButton = (Button) findViewById(R.id.button1);
		registrationButton = (Button) findViewById(R.id.button2);
		userNameEditText = (EditText) findViewById(R.id.editText1);
		passwordEditText = (EditText) findViewById(R.id.editText2);

		/* Listener for registration button */
		registrationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContent, RegistrationActivity.class);
				startActivity(intent);
			}
		});

		/* Listener for login button */

		loginButton.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (userNameEditText.getText().toString().trim().isEmpty()
						&& passwordEditText.getText().toString().trim()
								.isEmpty()) {
					pleaseinputToast();
				}

				if (userNameEditText.getText().toString().trim().isEmpty()
						&& !passwordEditText.getText().toString().trim()
								.isEmpty()) {
					userNameToast();
				}

				if (!userNameEditText.getText().toString().trim().isEmpty()
						&& passwordEditText.getText().toString().trim()
								.isEmpty()) {
					passwordToast();
				}

				if (!userNameEditText.getText().toString().trim().isEmpty()
						&& !passwordEditText.getText().toString().trim()
								.isEmpty()) {
					Intent intent = new Intent(mContent, MainActivity.class);
					startActivity(intent);
				}

			}
		});

	}

	public void userNameToast() {
		Toast.makeText(this, "Please input User Name", Toast.LENGTH_SHORT)
				.show();
	}

	public void passwordToast() {
		Toast.makeText(this, "Please input Password", Toast.LENGTH_SHORT)
				.show();
	}

	public void pleaseinputToast() {
		Toast.makeText(this, "Please input UserName and Password",
				Toast.LENGTH_SHORT).show();
	}

}
