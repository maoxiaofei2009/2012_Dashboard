package com.zjdx.dashboard;

import android.app.Application;
import android.widget.Toast;

/* adb logcat -v time > E:/log.txt */

public class MainApplication extends Application {
	private static final String TAG = "VehicleApp ";
	private static Toast mToast = null;
	private final static String DATA_PATH = "/data/data/com.zjdx.vehicle/";
	
	public void onCreate() {
		super.onCreate();
		loadLib();
		mToast = Toast.makeText(getApplicationContext(), " ", Toast.LENGTH_SHORT);	
	}
	
	public static void makeToast(String message){
		if (mToast != null){
			mToast.cancel();
			mToast.setText(message);
			mToast.show();
		}
	}
	
	private void loadLib() {
	}

}
