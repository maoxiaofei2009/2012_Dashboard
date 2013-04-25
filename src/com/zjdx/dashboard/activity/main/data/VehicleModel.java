package com.zjdx.dashboard.activity.main.data;

import com.zjdx.dashboard.activity.main.control.IControl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * VehicleModel 整个软件数据的中心,是UI与下机位的中介者。
 * 它通过VehicleThreadHandler类来实现与下位机通信的功能。
 * VehicleThreadHandler将通信结果（包括错误信息）以Handler消息机制（mUIMainHandler）
 * 返给VehicleModel。参见handleMessage(Message msg).
 * IControl 是UI的interface, VehicleModel通过它与UI交互。
 */
public class VehicleModel implements Handler.Callback {
	private static final String TAG = "Vehicle ";
	private IControl mControl;	
	private Handler mUIMainHandler;
	private VehicleThreadHandler mVehicleHandler = null;
	
	public VehicleModel(Context context, IControl i) {
		mControl = i;	
		SocketTransportImp.Instance();
		mUIMainHandler = new Handler(this);
		mVehicleHandler = new VehicleThreadHandler(context, mUIMainHandler);
	}
	
	public void onCreate() {			
	}
	
	public void onResume() {
		mVehicleHandler.setPausing(false);
	}
	
	public void onPause() {
		mVehicleHandler.setPausing(true);
	}
	
	public void onDestroy() {
		if (mVehicleHandler != null) {
			mVehicleHandler.finish();
			mVehicleHandler = null;
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		boolean res = true;			
		switch(msg.what) {
		case VehicleThreadHandler.RESPONSE_FAILED:
			onWarningMsg();
			break;
		case VehicleThreadHandler.RESPONSE_GET_INFO:
			onGetInfo(msg.arg1,msg.obj);
			break;
		default:
			res = false;
			break;
		}
		return res;
	}
	
	private void onWarningMsg() {
		if (mControl != null) {			
			mControl.showWarningMsg();
		}
	}
	
	private void onGetInfo(int event, Object obj) {
		if (mControl != null) {	
			mControl.upadteCarAlertStatus(event, obj);
		}
	}
	
}
