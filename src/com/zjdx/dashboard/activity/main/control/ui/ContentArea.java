package com.zjdx.dashboard.activity.main.control.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zjdx.dashboard.R;
import com.zjdx.dashboard.activity.main.control.IControl;
import com.zjdx.dashboard.activity.utils.ActivityUtils;


public class ContentArea {
	public static final String TAG = "ContentArea ";
	private final Context mContext;
	private IControl mIControl;
	
	private RelativeLayout mParentLayout;
	private RelativeLayout mDiscLeft;
	private ImageView mDiscLeftPointer;
	private ImageView mLeftAlert1;
	private ImageView mLeftAlert2;
	private ImageView mLeftAlert3;
	private ImageView mLeftAlert4;
	
	private RelativeLayout mDiscRight;
	private ImageView mDiscRightPointer;
	private ImageView mRightAlert1;
	private ImageView mRightAlert2;
	private ImageView mRightAlert3;
	private ImageView mRightAlert4;
	
	//top alert	
	private RelativeLayout mAlertTopArea;
	private TextView mCarStallsText;
	private TextView mFlightMileText;
	private TextView mTotalMileText;
	
	//center alert	
	private RelativeLayout mAlertCenterArea;
	private ImageView mCenterAlert1_fl;
	private ImageView mCenterAlert1_fr;
	private ImageView mCenterAlert1_bl;
	private ImageView mCenterAlert1_br;
	private ImageView mCenterAlert2;
	private ImageView mCenterAlert3;
	private ImageView mCenterAlert4;
	private ImageView mCenterAlert5;
	private ImageView mCenterAlert6;
	
	//bottom alert	
	private RelativeLayout mAlertBottomArea;
	private ImageView mBottomAlert1;
	private ImageView mBottomAlert2;
	private ImageView mBottomAlert3;
	private ImageView mBottomAlert4;
	private ImageView mBottomAlert5;
	private ImageView mBottomAlert6;
	private ImageView mBottomAlert7;
	private ImageView mBottomAlert8;
	
	
	//corner alert
	private ImageView mCornerTopLeftAlert;
	private ImageView mCornerTopRightAlert;
	private ImageView mCornerBottomLeftAlert;
	private ImageView mCornerBottomRightAlert;
	private TextView mCornerBottomLeftAlertText;
	private TextView mCornerBottomRightAlertText;
	
	public ContentArea(Context context, IControl control){
		mContext = context;
		mIControl = control;
		
		initLayout();
		initStatus();
	}
	
	private void initLayout(){
		mParentLayout = (RelativeLayout) ((Activity)mContext).findViewById(R.id.main_root);
		if (mParentLayout != null){
			initLeftDisc();
			initRightDisc();
			initAlertTop();
			initAlertBottom();
			initAlertCenter();
			initAlertCorner();
		}
	}
	
	private void initStatus(){
		boolean alert = true;
		//left disc alert
		upadteAlertStatus(IControl.EVENT_ENGINE_OIL_FAILURE_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_BATTERY_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_MAIN_ALERT, alert);
		upadteAlertStatus(IControl.ENGINE_EXHAUST_GAS_MONITOR_ALERT, alert);
		
		//right disc alert
		upadteAlertStatus(IControl.EVENT_BRAKE_SYSTEM_FAULT_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_ELECTRONIC_PARKING_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_EBD_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_ABS_ALERT, alert);

		//top alert
		upadteAlertStatus(IControl.EVENT_CAR_STALLS_ALERT, "P");
		upadteAlertStatus(IControl.EVENT_FLIGHT_MILE_ALERT, 0);
		upadteAlertStatus(IControl.EVENT_TOTAL_MILE_ALERT, 0);
		
		//center alert
		upadteAlertStatus(IControl.EVENT_CAR_DOOR_CHANGE_ALERT, new CarDoorState(false, true, true, true));
		upadteAlertStatus(IControl.EVENT_SAFTY_BELT_1_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_SAFTY_BELT_2_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_SAFTY_AIRBAG_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_ESP_TCS_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_AFS_OFF_ALERT, alert);
		
		//bottom alert
		upadteAlertStatus(IControl.EVENT_LAMP_LIGHT_NEAR_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_LAMP_LIGHT_FAR_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_LAMP_FOG_FRONT_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_LAMP_FOG_BEHIND_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_DOOR_FRONT_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_DOOR_BEHIND_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_BURGLAR_ALARM_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_ACTIVE_NIGHT_VISION_ALERT, alert);
		
		//corner alert
		upadteAlertStatus(IControl.EVENT_ROTATE_LEFT_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_ROTATE_RIGHT_ALERT, alert);
		upadteAlertStatus(IControl.EVENT_OIL_STORAGE_LOW_ALERT, 0.99f);
		upadteAlertStatus(IControl.EVENT_COOLING_FAULT_ALERT, 70);
	}
	
	private void initLeftDisc(){
		mDiscLeft = new RelativeLayout(mContext);
		//mDiscLeft.setBackgroundResource(R.drawable.main_left_disc);
		LayoutParams params = ActivityUtils.getLayoutParams(332, 322);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		mParentLayout.addView(mDiscLeft, params);
		
		ImageView LeftDisc = new ImageView(mContext);
		LeftDisc.setBackgroundResource(R.drawable.main_left_disc);
		params = ActivityUtils.getLayoutParams(296, 295);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mDiscLeft.addView(LeftDisc, params);
		
		mLeftAlert1 = new ImageView(mContext);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(70);
		params.bottomMargin = ActivityUtils.scaleY(85);
		mDiscLeft.addView(mLeftAlert1, params);
		
		mLeftAlert2 = new ImageView(mContext);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(116);
		params.bottomMargin = ActivityUtils.scaleY(55);
		mDiscLeft.addView(mLeftAlert2, params);
		
		mLeftAlert3 = new ImageView(mContext);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(167);
		params.bottomMargin = ActivityUtils.scaleY(55);
		mDiscLeft.addView(mLeftAlert3, params);
		
		mLeftAlert4 = new ImageView(mContext);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(208);
		params.bottomMargin = ActivityUtils.scaleY(85);
		mDiscLeft.addView(mLeftAlert4, params);
		
		mDiscLeftPointer = new ImageView(mContext);
		mDiscLeftPointer.setImageResource(R.drawable.main_left_disc_pointer);
		params = ActivityUtils.getLayoutParams(296, 295);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mDiscLeft.addView(mDiscLeftPointer, params);
	}
	
	private void initRightDisc(){
		mDiscRight = new RelativeLayout(mContext);
		//mDiscRight.setBackgroundResource(R.drawable.main_right_disc);
		LayoutParams params = ActivityUtils.getLayoutParams(332, 322);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mParentLayout.addView(mDiscRight, params);
		
		ImageView rightDisc = new ImageView(mContext);
		rightDisc.setBackgroundResource(R.drawable.main_right_disc);
		params = ActivityUtils.getLayoutParams(296, 295);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mDiscRight.addView(rightDisc, params);
		
		mRightAlert1 = new ImageView(mContext);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.rightMargin = ActivityUtils.scaleX(70);
		params.bottomMargin = ActivityUtils.scaleY(85);
		mDiscRight.addView(mRightAlert1, params);
		
		mRightAlert2 = new ImageView(mContext);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.rightMargin = ActivityUtils.scaleX(116);
		params.bottomMargin = ActivityUtils.scaleY(55);
		mDiscRight.addView(mRightAlert2, params);
		
		mRightAlert3 = new ImageView(mContext);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.rightMargin = ActivityUtils.scaleX(167);
		params.bottomMargin = ActivityUtils.scaleY(55);
		mDiscRight.addView(mRightAlert3, params);
		
		mRightAlert4 = new ImageView(mContext);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.rightMargin = ActivityUtils.scaleX(208);
		params.bottomMargin = ActivityUtils.scaleY(85);
		mDiscRight.addView(mRightAlert4, params);
		
		mDiscRightPointer = new ImageView(mContext);
		mDiscRightPointer.setImageResource(R.drawable.main_right_disc_pointer);
		params = ActivityUtils.getLayoutParams(296, 295);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mDiscRight.addView(mDiscRightPointer, params);
	}
	
	private void initAlertTop(){
		mAlertTopArea = new RelativeLayout(mContext);
		mAlertTopArea.setBackgroundResource(R.drawable.main_top_background);
		LayoutParams params = ActivityUtils.getLayoutParams(250, 98);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.topMargin = ActivityUtils.scaleY(23);
		mParentLayout.addView(mAlertTopArea, params);
		
		mCarStallsText = new TextView(mContext);
		mCarStallsText.setId(mCarStallsText.hashCode());
		mCarStallsText.setTextSize(mContext.getResources().getDimension(R.dimen.main_car_stalls));
		mCarStallsText.setText("P");
		mCarStallsText.setTypeface(Typeface.DEFAULT_BOLD);
		mCarStallsText.setTextColor(Color.GREEN);
		mCarStallsText.setGravity(Gravity.CENTER);
		params = ActivityUtils.getLayoutParams(30, 60);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(40);
		params.topMargin = ActivityUtils.scaleY(15);
		mAlertTopArea.addView(mCarStallsText, params);
		
		mFlightMileText = new TextView(mContext);
		mFlightMileText.setId(mFlightMileText.hashCode());
		mFlightMileText.setText("4356");
		mFlightMileText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mFlightMileText.setGravity(Gravity.CENTER);
		params = ActivityUtils.getLayoutParams(80, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.RIGHT_OF, mCarStallsText.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertTopArea.addView(mFlightMileText, params);
		
		TextView mFlightMileUnit = new TextView(mContext);
		mFlightMileUnit.setId(mFlightMileUnit.hashCode());
		mFlightMileUnit.setText("Km/h");
		mFlightMileUnit.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mFlightMileUnit.setGravity(Gravity.CENTER_VERTICAL);
		params = ActivityUtils.getLayoutParams(60, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.RIGHT_OF, mFlightMileText.getId());
		params.leftMargin = ActivityUtils.scaleX(5);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertTopArea.addView(mFlightMileUnit, params);
		
		mTotalMileText = new TextView(mContext);
		mTotalMileText.setId(mFlightMileText.hashCode());
		mTotalMileText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mTotalMileText.setText("689786");
		mTotalMileText.setGravity(Gravity.CENTER);
		params = ActivityUtils.getLayoutParams(80, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mCarStallsText.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertTopArea.addView(mTotalMileText, params);
		
		TextView mTotalMileUnit = new TextView(mContext);
		mTotalMileUnit.setId(mFlightMileUnit.hashCode());
		mTotalMileUnit.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		mTotalMileUnit.setText("Km/h");
		mTotalMileUnit.setGravity(Gravity.CENTER_VERTICAL);
		params = ActivityUtils.getLayoutParams(60, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mTotalMileText.getId());
		params.leftMargin = ActivityUtils.scaleX(5);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertTopArea.addView(mTotalMileUnit, params);
	}
	
	private void initAlertBottom(){
		mAlertBottomArea = new RelativeLayout(mContext);
		mAlertBottomArea.setBackgroundResource(R.drawable.main_bottom_background);
		LayoutParams params = ActivityUtils.getLayoutParams(283, 97);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.bottomMargin = ActivityUtils.scaleY(23);
		mParentLayout.addView(mAlertBottomArea, params);
		
		mBottomAlert1 = new ImageView(mContext);
		mBottomAlert1.setId(mBottomAlert1.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(40);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertBottomArea.addView(mBottomAlert1, params);
		
		mBottomAlert2 = new ImageView(mContext);
		mBottomAlert2.setId(mBottomAlert2.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAlert1.getId());
		params.leftMargin = ActivityUtils.scaleX(2);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertBottomArea.addView(mBottomAlert2, params);
		
		mBottomAlert3 = new ImageView(mContext);
		mBottomAlert3.setId(mBottomAlert3.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAlert2.getId());
		params.leftMargin = ActivityUtils.scaleX(2);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertBottomArea.addView(mBottomAlert3, params);
		
		mBottomAlert4 = new ImageView(mContext);
		mBottomAlert4.setId(mBottomAlert4.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAlert3.getId());
		params.leftMargin = ActivityUtils.scaleX(2);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertBottomArea.addView(mBottomAlert4, params);
		
		mBottomAlert5 = new ImageView(mContext);
		mBottomAlert5.setId(mBottomAlert5.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(30);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertBottomArea.addView(mBottomAlert5, params);
		
		mBottomAlert6 = new ImageView(mContext);
		mBottomAlert6.setId(mBottomAlert6.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAlert5.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertBottomArea.addView(mBottomAlert6, params);
		
		mBottomAlert7 = new ImageView(mContext);
		mBottomAlert7.setId(mBottomAlert7.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAlert6.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertBottomArea.addView(mBottomAlert7, params);
		
		mBottomAlert8 = new ImageView(mContext);
		mBottomAlert8.setId(mBottomAlert8.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mBottomAlert7.getId());
		params.leftMargin = ActivityUtils.scaleX(10);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertBottomArea.addView(mBottomAlert8, params);
	}
	
	private void initAlertCenter(){
		mAlertCenterArea = new RelativeLayout(mContext);
		LayoutParams params = ActivityUtils.getLayoutParams(160, 110);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		mParentLayout.addView(mAlertCenterArea, params);
		
		mCenterAlert1_fl = new ImageView(mContext);
		mCenterAlert1_fl.setId(mCenterAlert1_fl.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(1);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert1_fl, params);
		
		mCenterAlert1_fr = new ImageView(mContext);
		mCenterAlert1_fr.setId(mCenterAlert1_fr.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(1);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert1_fr, params);
		
		mCenterAlert1_bl = new ImageView(mContext);
		mCenterAlert1_bl.setId(mCenterAlert1_bl.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(1);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert1_bl, params);
		
		mCenterAlert1_br = new ImageView(mContext);
		mCenterAlert1_br.setId(mCenterAlert1_br.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(1);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert1_br, params);
		
		mCenterAlert2 = new ImageView(mContext);
		mCenterAlert2.setId(mCenterAlert2.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.RIGHT_OF, mCenterAlert1_fl.getId());
		params.leftMargin = ActivityUtils.scaleX(1);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert2, params);
		
		mCenterAlert3 = new ImageView(mContext);
		mCenterAlert3.setId(mCenterAlert3.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.RIGHT_OF, mCenterAlert2.getId());
		params.leftMargin = ActivityUtils.scaleX(1);
		params.topMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert3, params);
		

		
		mCenterAlert4 = new ImageView(mContext);
		mCenterAlert4.setId(mCenterAlert4.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.leftMargin = ActivityUtils.scaleX(1);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert4, params);
		
		mCenterAlert5 = new ImageView(mContext);
		mCenterAlert5.setId(mCenterAlert5.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mCenterAlert4.getId());
		params.leftMargin = ActivityUtils.scaleX(1);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert5, params);
		
		mCenterAlert6 = new ImageView(mContext);
		mCenterAlert6.setId(mCenterAlert6.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, mCenterAlert5.getId());
		params.leftMargin = ActivityUtils.scaleX(1);
		params.bottomMargin = ActivityUtils.scaleY(8);
		mAlertCenterArea.addView(mCenterAlert6, params);
	}
	
	private void initAlertCorner(){
		mCornerTopLeftAlert = new ImageView(mContext);
		mCornerTopLeftAlert.setId(mCornerTopLeftAlert.hashCode());
		LayoutParams params = ActivityUtils.getLayoutParams(56, 56);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.leftMargin = ActivityUtils.scaleX(31);
		params.topMargin = ActivityUtils.scaleY(29);
		mParentLayout.addView(mCornerTopLeftAlert, params);
		
		mCornerTopRightAlert = new ImageView(mContext);
		mCornerTopRightAlert.setId(mCornerTopRightAlert.hashCode());
		params = ActivityUtils.getLayoutParams(56, 56);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.rightMargin = ActivityUtils.scaleX(31);
		params.topMargin = ActivityUtils.scaleY(29);
		mParentLayout.addView(mCornerTopRightAlert, params);
		
		mCornerBottomLeftAlert = new ImageView(mContext);
		mCornerBottomLeftAlert.setId(mCornerBottomLeftAlert.hashCode());
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(35);
		params.bottomMargin = ActivityUtils.scaleY(33);
		mParentLayout.addView(mCornerBottomLeftAlert, params);
		
		mCornerBottomLeftAlertText = new TextView(mContext);
		mCornerBottomLeftAlertText.setId(mCornerBottomLeftAlertText.hashCode());
		mCornerBottomLeftAlertText.setGravity(Gravity.BOTTOM);
		mCornerBottomLeftAlertText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		params = ActivityUtils.getLayoutParams(60, 48);
		params.addRule(RelativeLayout.RIGHT_OF, mCornerBottomLeftAlert.getId());
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.leftMargin = ActivityUtils.scaleX(15);
		params.bottomMargin = ActivityUtils.scaleY(33);
		mParentLayout.addView(mCornerBottomLeftAlertText, params);
		
		mCornerBottomRightAlert = new ImageView(mContext);
		mCornerBottomRightAlert.setId(mCornerBottomRightAlert.hashCode());
		mCornerBottomRightAlert.setImageResource(R.drawable.alert_cooling_fault_green);
		params = ActivityUtils.getLayoutParams(48, 48);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.rightMargin = ActivityUtils.scaleX(35);
		params.bottomMargin = ActivityUtils.scaleY(33);
		mParentLayout.addView(mCornerBottomRightAlert, params);
		
		mCornerBottomRightAlertText = new TextView(mContext);
		mCornerBottomRightAlertText.setId(mCornerBottomLeftAlertText.hashCode());
		mCornerBottomRightAlertText.setGravity(Gravity.BOTTOM);
		mCornerBottomRightAlertText.setTextSize(mContext.getResources().getDimension(R.dimen.main_text_content));
		params = ActivityUtils.getLayoutParams(60, 48);
		params.addRule(RelativeLayout.LEFT_OF, mCornerBottomRightAlert.getId());
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.rightMargin = ActivityUtils.scaleX(15);
		params.bottomMargin = ActivityUtils.scaleY(33);
		mParentLayout.addView(mCornerBottomRightAlertText, params);
	}
	
	
	public void upadteAlertStatus(int event, Object obj) {
		switch (event) {
		//left disc alert
		case IControl.EVENT_ENGINE_OIL_FAILURE_ALERT:
			updateLeftAlert1((Boolean) obj);
			break;
		case IControl.EVENT_BATTERY_ALERT:
			updateLeftAlert2((Boolean) obj);
			break;
		case IControl.EVENT_MAIN_ALERT:
			updateLeftAlert3((Boolean) obj);
			break;
		case IControl.ENGINE_EXHAUST_GAS_MONITOR_ALERT:
			updateLeftAlert4((Boolean) obj);
			break;
		
		//right disc alert
		case IControl.EVENT_BRAKE_SYSTEM_FAULT_ALERT:
			updateRightAlert1((Boolean) obj);
			break;
		case IControl.EVENT_ELECTRONIC_PARKING_ALERT:
			updateRightAlert2((Boolean) obj);
			break;
		case IControl.EVENT_EBD_ALERT:
			updateRightAlert3((Boolean) obj);
			break;
		case IControl.EVENT_ABS_ALERT:
			updateRightAlert4((Boolean) obj);
			break;
			
		//top alert
		case IControl.EVENT_CAR_STALLS_ALERT:
			updateCarStalls((String) obj);
			break;
		case IControl.EVENT_FLIGHT_MILE_ALERT:
			updateFlightMile((Integer) obj);
			break;
		case IControl.EVENT_TOTAL_MILE_ALERT:
			updatemTotalMile((Integer) obj);
			break;

		//center alert
		case IControl.EVENT_CAR_DOOR_CHANGE_ALERT:
			updateCenterAlert1((CarDoorState) obj);
			break;
		case IControl.EVENT_SAFTY_BELT_1_ALERT:
			updateCenterAlert2((Boolean) obj);
			break;
		case IControl.EVENT_SAFTY_BELT_2_ALERT:
			updateCenterAlert3((Boolean) obj);
			break;
		case IControl.EVENT_SAFTY_AIRBAG_ALERT:
			updateCenterAlert4((Boolean) obj);
			break;
		case IControl.EVENT_ESP_TCS_ALERT:
			updateCenterAlert5((Boolean) obj);
			break;
		case IControl.EVENT_AFS_OFF_ALERT:
			updateCenterAlert6((Boolean) obj);
			break;
		
		//bottom alert
		case IControl.EVENT_LAMP_LIGHT_NEAR_ALERT:
			updateBottomAlert1((Boolean) obj);
			break;
		case IControl.EVENT_LAMP_LIGHT_FAR_ALERT:
			updateBottomAlert2((Boolean) obj);
			break;
		case IControl.EVENT_LAMP_FOG_FRONT_ALERT:
			updateBottomAlert3((Boolean) obj);
			break;
		case IControl.EVENT_LAMP_FOG_BEHIND_ALERT:
			updateBottomAlert4((Boolean) obj);
			break;
		case IControl.EVENT_DOOR_FRONT_ALERT:
			updateBottomAlert5((Boolean) obj);
			break;
		case IControl.EVENT_DOOR_BEHIND_ALERT:
			updateBottomAlert6((Boolean) obj);
			break;
		case IControl.EVENT_BURGLAR_ALARM_ALERT:
			updateBottomAlert7((Boolean) obj);
			break;
		case IControl.EVENT_ACTIVE_NIGHT_VISION_ALERT:
			updateBottomAlert8((Boolean) obj);
			break;
			
		//corner alert
		case IControl.EVENT_ROTATE_LEFT_ALERT:
			updateCornerAlert1((Boolean) obj);
			break;
		case IControl.EVENT_ROTATE_RIGHT_ALERT:
			updateCornerAlert2((Boolean) obj);
			break;
		case IControl.EVENT_OIL_STORAGE_LOW_ALERT:
			updateCornerAlert3((Float) obj);
			break;
		case IControl.EVENT_COOLING_FAULT_ALERT:
			updateCornerAlert4((Integer) obj);
			break;
		default:
			break;
		}
	}
	
	//========================================
	private void updateLeftAlert1(boolean alert){
		mLeftAlert1.setImageResource(alert? R.drawable.alert_engine_oil_failure_highlight
				: R.drawable.alert_engine_oil_failure_normal);
	}
	
	private void updateLeftAlert2(boolean alert){
		mLeftAlert2.setImageResource(alert? R.drawable.alert_battery_highlight
				: R.drawable.alert_battery_normal);
	}
	
	private void updateLeftAlert3(boolean alert){
		mLeftAlert3.setImageResource(alert? R.drawable.alert_main_alert_highlight
				: R.drawable.alert_main_alert);
	}
	
	private void updateLeftAlert4(boolean alert){
		mLeftAlert4.setImageResource(alert? R.drawable.alert_engine_exhaust_gas_monitor_highlight
				: R.drawable.alert_engine_exhaust_gas_monitor_normal);
	}
	
	//==========================================
	private void updateRightAlert1(boolean alert){
		mRightAlert1.setImageResource(alert? R.drawable.alert_brake_system_fault_highlight
				: R.drawable.alert_brake_system_fault_normal);
	}
	
	private void updateRightAlert2(boolean alert){
		mRightAlert2.setImageResource(alert? R.drawable.alert_electronic_parking_highlight
				: R.drawable.alert_electronic_parking_normal);
	}
	
	private void updateRightAlert3(boolean alert){
		mRightAlert3.setImageResource(alert? R.drawable.alert_ebd_highlight
				: R.drawable.alert_ebd_normal);
	}
	
	private void updateRightAlert4(boolean alert){
		mRightAlert4.setImageResource(alert? R.drawable.alert_abs_highlight
				: R.drawable.alert_abs_normal);
	}
	//=========================================
	
	private void updateCarStalls(String carStalls){
		mCarStallsText.setText(carStalls);
	}
	
	private void updateFlightMile(int mile){
		mFlightMileText.setText("" + mile);
	}
	
	private void updatemTotalMile(int mile){
		mTotalMileText.setText("" + mile);
	}
	
	//===========================================
	private void updateCenterAlert1(CarDoorState state){
		if (!state.mBehindLeftOpen && !state.mBehindRightOpen
			&& !state.mFrontLeftOpen && !state.mFrontRightOpen){
			mCenterAlert1_fl.setImageResource(R.drawable.alert_cardoor_close_all);
			mCenterAlert1_fr.setImageResource(0);
			mCenterAlert1_bl.setImageResource(0);
			mCenterAlert1_br.setImageResource(0);
		}else{
			mCenterAlert1_fl.setImageResource(state.mFrontLeftOpen ? 
					R.drawable.alert_cardoor_front_left_open : 0);
			mCenterAlert1_fr.setImageResource(state.mFrontRightOpen ? 
					R.drawable.alert_cardoor_front_right_open : 0);
			mCenterAlert1_bl.setImageResource(state.mBehindLeftOpen ? 
					R.drawable.alert_cardoor_behind_left_open : 0);
			mCenterAlert1_br.setImageResource(state.mBehindRightOpen ? 
					R.drawable.alert_cardoor_behind_right_open : 0);
		}
	}
	
	private void updateCenterAlert2(boolean alert){
		mCenterAlert2.setImageResource(alert? R.drawable.alert_safty_belt_normal_1_highlight
				: R.drawable.alert_safty_belt_normal_1);
	}
	
	private void updateCenterAlert3(boolean alert){
		mCenterAlert3.setImageResource(alert? R.drawable.alert_safty_belt_normal_2_highlight
				: R.drawable.alert_safty_belt_normal_2);
	}
	
	private void updateCenterAlert4(boolean alert){
		mCenterAlert4.setImageResource(alert? R.drawable.alert_safty_airbag_highlight
				: R.drawable.alert_safty_airbag_normal);
	}
	
	private void updateCenterAlert5(boolean alert){
		mCenterAlert5.setImageResource(alert? R.drawable.alert_esp_tcs_highlight
				: R.drawable.alert_esp_tcs);
	}
	
	private void updateCenterAlert6(boolean alert){
		mCenterAlert6.setImageResource(alert? R.drawable.alert_afs_off_highlight
				: R.drawable.alert_afs_off);
	}
	
	//===============================================
	private void updateBottomAlert1(boolean alert){
		mBottomAlert1.setImageResource(alert? R.drawable.alert_dock_lamp_light_near_open
				: R.drawable.alert_dock_lamp_light_near_close);
	}

	private void updateBottomAlert2(boolean alert){
		mBottomAlert2.setImageResource(alert? R.drawable.alert_dock_lamp_light_far_open
				: R.drawable.alert_dock_lamp_light_far_close);
	}
	
	private void updateBottomAlert3(boolean alert){
		mBottomAlert3.setImageResource(alert? R.drawable.alert_dock_lamp_fog_front_open
				: R.drawable.alert_dock_lamp_fog_front_close);
	}

	private void updateBottomAlert4(boolean alert){
		mBottomAlert4.setImageResource(alert? R.drawable.alert_dock_lamp_fog_behind_open
				: R.drawable.alert_dock_lamp_fog_behind_close);
	}
	
	private void updateBottomAlert5(boolean alert){
		mBottomAlert5.setImageResource(alert? R.drawable.alert_dock_door_front_open
				: R.drawable.alert_dock_door_front_close);
	}

	private void updateBottomAlert6(boolean alert){
		mBottomAlert6.setImageResource(alert? R.drawable.alert_dock_door_behind_open
				: R.drawable.alert_dock_door_behind_close);
	}
	
	private void updateBottomAlert7(boolean alert){
		mBottomAlert7.setImageResource(alert? R.drawable.alert_burglar_alarm_highlight
				: R.drawable.alert_burglar_alarm_normal);
	}

	private void updateBottomAlert8(boolean alert){
		mBottomAlert8.setImageResource(alert? R.drawable.alert_active_night_vision_highlight
				: R.drawable.alert_active_night_vision_normal);
	}
	//============================================
	private AnimationDrawable mAnimationDrawable1 = null;
	private void updateCornerAlert1(boolean alert){
		if (mAnimationDrawable1 != null){
			mAnimationDrawable1.stop();
		}
		
		mCornerTopLeftAlert.setImageResource(alert? R.drawable.main_rotate_left_alert_lamp
				: R.drawable.icon_lamp_rotate_left_normal);
		if (alert){
			mAnimationDrawable1 = (AnimationDrawable) mCornerTopLeftAlert.getDrawable();
			mAnimationDrawable1.start();
		}
	}
	
	private AnimationDrawable mAnimationDrawable2 = null;
	private void updateCornerAlert2(boolean alert){
		if (mAnimationDrawable2 != null){
			mAnimationDrawable2.stop();
		}
		mCornerTopRightAlert.setImageResource(alert? R.drawable.main_rotate_right_alert_lamp :
			R.drawable.icon_lamp_rotate_right_normal);
		if (alert){
			mAnimationDrawable2 = (AnimationDrawable) mCornerTopRightAlert.getDrawable();
			mAnimationDrawable2.start();
		}
	}
	
	private void updateCornerAlert3(float percent){
		mCornerBottomLeftAlert.setImageResource((percent>0.5)? R.drawable.alert_oil_storage_low_green
				: R.drawable.alert_oil_storage_low_red);
		mCornerBottomLeftAlertText.setText(String.format("%.0f", (100 * percent)) + "  %");
	}
	
	private void updateCornerAlert4(float temperature){
		mCornerBottomRightAlert.setImageResource((temperature > 50)? R.drawable.alert_cooling_fault_green
				: R.drawable.alert_cooling_fault_red);
		mCornerBottomRightAlertText.setText(String.format("%.0f", temperature) + "  ¡æ");
	}
	//=============================================
}