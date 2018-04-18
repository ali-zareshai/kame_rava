/* Copyright 2014 Sheldon Neilson www.neilson.co.za
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.example.ali.shiva;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class AlarmAlertActivity extends Activity implements OnClickListener {

	long timeSeconds1;
	long timeSeconds2;
	FloatingActionButton floatingActionButton;
	SharedPreferences SP;

	String[] lang=new String[2];

	private StringBuilder answerBuilder = new StringBuilder();


	private boolean alarmActive;

	private TextView problemView;
	private TextView answerView;
	private String answerString;
	Util util;
	boolean ss2;

	Timer t;
	Timer timer;
	int i;

	@Override
	protected void onDestroy() {
		if (ss2){
			startService(new Intent(AlarmAlertActivity.this,StartAlarm2.class));
		}
		super.onDestroy();
	}


	@Override
	protected void onStop() {
		Util.active=false;
		super.onStop();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ss2=true;
		Util.active=true;
		SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		final Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		util=new Util(getApplicationContext());

		////////////////////////////////////////////////////////
		util.startAlarm();
		setContentView(R.layout.alarm_alert);
		MyMath.setLevel(SP.getString("problem_level","4"));
		long timeMillis2 = System.currentTimeMillis();
		timeSeconds1 = TimeUnit.MILLISECONDS.toSeconds(timeMillis2);


		answerString = String.valueOf(MyMath.getJavab());
		Log.e("answerString",answerString);
		if (answerString.endsWith(".0")) {
			answerString = answerString.substring(0, answerString.length() - 2);
		}

		problemView = (TextView) findViewById(R.id.textView1);
		problemView.setText(MyMath.getProblem());

		answerView = (TextView) findViewById(R.id.textView2);
		answerView.setText("= ?");

		findViewById(R.id.Button0).setOnClickListener(this);
		findViewById(R.id.Button1).setOnClickListener(this);
		findViewById(R.id.Button2).setOnClickListener(this);
		findViewById(R.id.Button3).setOnClickListener(this);
		findViewById(R.id.Button4).setOnClickListener(this);
		findViewById(R.id.Button5).setOnClickListener(this);
		findViewById(R.id.Button6).setOnClickListener(this);
		findViewById(R.id.Button7).setOnClickListener(this);
		findViewById(R.id.Button8).setOnClickListener(this);
		findViewById(R.id.Button9).setOnClickListener(this);
		findViewById(R.id.Button_clear).setOnClickListener(this);
		findViewById(R.id.Button_decimal).setOnClickListener(this);
		findViewById(R.id.Button_minus).setOnClickListener(this);
		floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButtonmutealer);
		floatingActionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				util.setMute();
			}
		});


//
		timer=new Timer();
		timer.schedule(timerTask,1000,3000);

	}

	@Override
	protected void onResume() {
		super.onResume();
		alarmActive = true;
	}

	@Override
	public void onBackPressed() {

	}

	@Override
	protected void onPause() {
		super.onPause();
		StaticWakeLock.lockOff(this);
	}


	TimerTask timerTask=new TimerTask() {
		@Override
		public void run() {
			long timeMillis = System.currentTimeMillis();
			timeSeconds2 = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
			long remin=timeSeconds2-timeSeconds1;
			Log.e("SP SHAREED*******",SP.getString("alarm_time","60"));
			if (remin>=Integer.parseInt(SP.getString("alarm_time","60"))){
				timerTask.cancel();
				util.finshActivity();
				finish();
			}

		}
	};

	@Override
	public void onClick(View v) {
		if (!alarmActive)
			return;
		String button = (String) v.getTag();
		v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
		if (button.equalsIgnoreCase("clear")) {
			if (answerBuilder.length() > 0) {
				answerBuilder.setLength(answerBuilder.length() - 1);
				answerView.setText(answerBuilder.toString());
			}
		} else if (button.equalsIgnoreCase(".")) {
			if (!answerBuilder.toString().contains(button)) {
				if (answerBuilder.length() == 0)
					answerBuilder.append(0);
				answerBuilder.append(button);
				answerView.setText(answerBuilder.toString());
			}
		} else if (button.equalsIgnoreCase("-")) {
			if (answerBuilder.length() == 0) {
				answerBuilder.append(button);
				answerView.setText(answerBuilder.toString());
			}
		} else {
			answerBuilder.append(button);
			answerView.setText(answerBuilder.toString());
			if (isAnswerCorrect()) {
				ss2=false;
				timerTask.cancel();
				util.endsus();
				timer.cancel();
				finish();
			}
		}
		if (answerView.getText().length() >= answerString.length()
				&& !isAnswerCorrect()) {
			answerView.setTextColor(Color.RED);
		} else {
			answerView.setTextColor(Color.BLACK);
		}
	}

	public boolean isAnswerCorrect() {
		boolean correct = false;
		try {
			correct =  MyMath.getJavab() == Float.parseFloat(answerBuilder
					.toString());
		} catch (NumberFormatException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return correct;
	}
	private static class MyMath {
		private static String problem,level;
		private static int a,b,c,d,javab;

		public static void setLevel(String level) {
			MyMath.level = level;
		}

		public static String getProblem(){
			a=new Random().nextInt(10);
			b=new Random().nextInt(9);
			c=new Random().nextInt(10);
			d=new Random().nextInt(9);
			switch (level){
				case "3":
					problem="("+a+"+"+b+")+("+c+"+"+d+")";
					break;
				case "4":
					problem="("+a+"+"+b+")*("+c+"+"+d+")";
					break;
				case "5":
					problem="("+a+"*"+b+")+("+c+"*"+d+")";
					break;
			}
			return problem;
		}
		public static int getJavab(){
			switch (level){
				case "3":
					javab=(a+b)+(c+d);
					break;
				case "4":
					javab=(a+b)*(c+d);
					break;
				case "5":
					javab=(a*b)+(c*d);
					break;
			}
			return javab;
		}
	}
}
