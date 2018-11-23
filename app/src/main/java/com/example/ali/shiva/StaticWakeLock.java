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

import android.content.Context;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.view.WindowManager;

public class StaticWakeLock {
	private static PowerManager.WakeLock wl = null;
	private static SensorManager mSensorManager;
	private static PowerManager mPowerManager;
	private static WindowManager mWindowManager;
	private static PowerManager.WakeLock mWakeLock;

	public static void lockOn(Context context) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		//Object flags;
		if (wl == null)
			wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "MATH_ALARM");
		wl.acquire();
	}

	public static void lockOff(Context context) {
//		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//		try {
//			if (wl != null)
//				wl.release();
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
		try{
			// Get an instance of the SensorManager
			mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);

			// Get an instance of the PowerManager
			mPowerManager = (PowerManager) context.getSystemService(context.POWER_SERVICE);

			// Get an instance of the WindowManager
			mWindowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
			mWindowManager.getDefaultDisplay();

			// Create a bright wake lock
			mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, StaticWakeLock.class.getName());
			mWakeLock.release();



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // END onCreate

}