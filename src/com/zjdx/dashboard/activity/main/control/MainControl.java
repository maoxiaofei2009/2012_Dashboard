package com.zjdx.dashboard.activity.main.control;

import android.content.Context;

import com.zjdx.dashboard.MainApplication;
import com.zjdx.dashboard.activity.main.control.ui.ContentArea;


public class MainControl implements IControl{
	public static final String TAG = "MainControl ";
	
	public ContentArea mContentArea;
	public MainControl(Context context){
		mContentArea = new ContentArea(context, this);
	}
	
	@Override
	public void upadteCarAlertStatus(int event, Object obj) {
		switch (event) {
		//left disc alert
		case IControl.EVENT_ENGINE_OIL_FAILURE_ALERT:
		case IControl.EVENT_BATTERY_ALERT:
		case IControl.EVENT_MAIN_ALERT:
		case IControl.ENGINE_EXHAUST_GAS_MONITOR_ALERT:
		
		//right disc alert
		case IControl.EVENT_BRAKE_SYSTEM_FAULT_ALERT:
		case IControl.EVENT_ELECTRONIC_PARKING_ALERT:
		case IControl.EVENT_EBD_ALERT:
		case IControl.EVENT_ABS_ALERT:
			
		//top alert
		case IControl.EVENT_CAR_STALLS_ALERT:
		case IControl.EVENT_FLIGHT_MILE_ALERT:
		case IControl.EVENT_TOTAL_MILE_ALERT:

		//center alert
		case IControl.EVENT_CAR_DOOR_CHANGE_ALERT:
		case IControl.EVENT_SAFTY_BELT_1_ALERT:
		case IControl.EVENT_SAFTY_BELT_2_ALERT:
		case IControl.EVENT_SAFTY_AIRBAG_ALERT:
		case IControl.EVENT_ESP_TCS_ALERT:
		case IControl.EVENT_AFS_OFF_ALERT:
		
		//bottom alert
		case IControl.EVENT_LAMP_LIGHT_NEAR_ALERT:
		case IControl.EVENT_LAMP_LIGHT_FAR_ALERT:
		case IControl.EVENT_LAMP_FOG_FRONT_ALERT:
		case IControl.EVENT_LAMP_FOG_BEHIND_ALERT:
		case IControl.EVENT_DOOR_FRONT_ALERT:
		case IControl.EVENT_DOOR_BEHIND_ALERT:
		case IControl.EVENT_BURGLAR_ALARM_ALERT:
		case IControl.EVENT_ACTIVE_NIGHT_VISION_ALERT:
			
		//corner alert
		case IControl.EVENT_ROTATE_LEFT_ALERT:
		case IControl.EVENT_ROTATE_RIGHT_ALERT:
		case IControl.EVENT_OIL_STORAGE_LOW_ALERT:
		case IControl.EVENT_COOLING_FAULT_ALERT:
			mContentArea.upadteAlertStatus(event, obj);
			break;
		default:
			break;
		}
	}

	@Override
	public void showWarningMsg() {		
		MainApplication.makeToast("获取数据失败");			
	}
	
	
}