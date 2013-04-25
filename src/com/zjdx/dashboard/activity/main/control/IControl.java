package com.zjdx.dashboard.activity.main.control;


public interface IControl {
	public static final String TAG = "MainControl ";
	
	//left disc alert
	public static final int EVENT_ENGINE_OIL_FAILURE_ALERT = 11;
	public static final int EVENT_BATTERY_ALERT = 12;
	public static final int EVENT_MAIN_ALERT = 13;
	public static final int ENGINE_EXHAUST_GAS_MONITOR_ALERT = 14;
	
	//right disc alert
	public static final int EVENT_BRAKE_SYSTEM_FAULT_ALERT = 21;
	public static final int EVENT_ELECTRONIC_PARKING_ALERT = 22;
	public static final int EVENT_EBD_ALERT = 23;
	public static final int EVENT_ABS_ALERT = 24;
	
	//top alert
	public static final int EVENT_CAR_STALLS_ALERT = 31;
	public static final int EVENT_FLIGHT_MILE_ALERT = 32;
	public static final int EVENT_TOTAL_MILE_ALERT = 33;

	//center alert
	public static final int EVENT_CAR_DOOR_CHANGE_ALERT = 41;
	public static final int EVENT_SAFTY_BELT_1_ALERT = 42;
	public static final int EVENT_SAFTY_BELT_2_ALERT = 43;
	public static final int EVENT_SAFTY_AIRBAG_ALERT = 44;
	public static final int EVENT_ESP_TCS_ALERT = 45;
	public static final int EVENT_AFS_OFF_ALERT = 46;
	
	//bottom alert
	public static final int EVENT_LAMP_LIGHT_NEAR_ALERT = 51;
	public static final int EVENT_LAMP_LIGHT_FAR_ALERT = 52;
	public static final int EVENT_LAMP_FOG_FRONT_ALERT = 53;
	public static final int EVENT_LAMP_FOG_BEHIND_ALERT = 54;
	public static final int EVENT_DOOR_FRONT_ALERT = 55;
	public static final int EVENT_DOOR_BEHIND_ALERT = 56;
	public static final int EVENT_BURGLAR_ALARM_ALERT = 57;
	public static final int EVENT_ACTIVE_NIGHT_VISION_ALERT = 58;
	
	//corner alert
	public static final int EVENT_ROTATE_LEFT_ALERT = 71;
	public static final int EVENT_ROTATE_RIGHT_ALERT = 72;
	public static final int EVENT_OIL_STORAGE_LOW_ALERT = 73;
	public static final int EVENT_COOLING_FAULT_ALERT = 74;
	
	public void upadteCarAlertStatus(int event, Object obj);
	
	public void showWarningMsg();
}