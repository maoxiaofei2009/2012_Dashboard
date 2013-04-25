package com.zjdx.dashboard.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.zjdx.dashboard.R;
import com.zjdx.dashboard.activity.main.control.MainControl;
import com.zjdx.dashboard.activity.main.data.VehicleModel;
import com.zjdx.dashboard.activity.utils.ActivityUtils;

public class DashboardActivity extends Activity {
	private VehicleModel mVehicleModel;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initScreenSize();
        setContentView(R.layout.main);
        
        MainControl mainControl = new MainControl(this);
        mVehicleModel = new VehicleModel(this, mainControl);
        mVehicleModel.onCreate();
    }
    
    private void initScreenSize() {
    	/** Get screen width and height */
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
			
		int w = display.getWidth() < display.getHeight()? display.getHeight() :  display.getWidth();
		int h = display.getWidth() < display.getHeight()? display.getWidth() :  display.getHeight();
    
		/** Get Android DPI from system*/
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		ActivityUtils.scaleInit(w, h, dm.densityDpi, 800, 480, 240);
    }
    
    protected void onResume() {
    	super.onResume();
    	mVehicleModel.onResume();
    }
    
    protected void onPause() {
    	mVehicleModel.onPause();
    	super.onPause();
    }
    
    protected void onDestroy() {
    	mVehicleModel.onDestroy();
    	super.onDestroy();
    }
}