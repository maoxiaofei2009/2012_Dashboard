package com.zjdx.dashboard.activity.main.data;


import uiOperation.DiagnosticStart;
import uiOperation.GetInfo;

import com.zjdx.dashboard.LogUtils;
import com.zjdx.dashboard.activity.main.control.IControl;
import com.zjdx.dashboard.activity.main.control.ui.CarDoorState;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * VehicleThreadHandler 在LooperThread线程中通过TCP/IP与下位机通讯。
 * 在TCP第一次成功连接之后，调用DiagnosticStart,初始化下位机通信状态。（参见doInit）
 * 在DiagnosticStart成功完成init工作之后，定时查询下机位状态值。（参见getAllInfo）
 * 当Activity退出之时，调用finish终结LooperThread.
 *
 */
public class VehicleThreadHandler implements Handler.Callback {
	private static final String TAG = "VehicleThreadHandler ";
	private static final int WARNING_COUNT = 3;
	
	private static final int INIT 			= 0x01;
	private static final int DESTROY 		= 0x02;
	private static final int GET_INFO_ALL	= 0x03;
	
	public static final int RESPONSE_FAILED	= 0x01;
	public static final int RESPONSE_GET_INFO= 0x02;;
	
	private boolean mPausing = false;
	private boolean mQuiting = false;
	private boolean mVehicleSessionValid = false;
		
	private Handler mHandler;
	private LooperThread mThread;
	private Handler mModelHandler;	
	private DiagnosticStart mDiagnosticStart; 
	private GetInfo mGetInfo;
	
	public VehicleThreadHandler(Context context, Handler modelHandler) {
		mModelHandler = modelHandler; 
		mThread = new LooperThread();
		mThread.start();
		mHandler = new Handler(mThread.getLooper(), this);			
	}
	
	public synchronized void setPausing(boolean pasuing) {
		LogUtils.LOGD(TAG, "setPausing " + pasuing + " <----");
		mPausing = pasuing;
		
		if (pasuing) {
			mHandler.removeMessages(INIT);
			mHandler.removeMessages(GET_INFO_ALL);
			
			if (mDiagnosticStart != null) {
				mDiagnosticStart.tryQuit();		
			}
		}
		else {
			keepAlive();
		}
		LogUtils.LOGD(TAG, "setPausing " + pasuing + " ---->");
	}
	
	public void finish() {
		LogUtils.LOGD(TAG, "finish <----");
		if (mDiagnosticStart != null) {
			mDiagnosticStart.tryQuit();		
		}
		
		if (!mQuiting && mHandler != null) {		
			Message msg = new Message();
			msg.what = DESTROY;
			mHandler.sendMessageAtFrontOfQueue(msg);
		}
		
		try {
			if (mThread != null) {
				mQuiting = true;		
				mThread.getLooper().quit();			
				mThread.join();			
				mThread = null;
			}				
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LogUtils.LOGD(TAG, "finish ---->");
	}
	
	
	@Override
	public boolean handleMessage(Message msg) {		
		if (mQuiting && msg.what != DESTROY) 
			return true;		
		
		switch (msg.what) {	
		case INIT:
			doInit();
			break;
		case GET_INFO_ALL:
			getAllInfo();
			break;
		case DESTROY:
			doUninit();
			break;
		default:
			break;
		}
		keepAlive();
		
		return true;
	}
	
	private synchronized void keepAlive() {
		if (mQuiting || mPausing) 
			return;
		
		int what = !mVehicleSessionValid? INIT : GET_INFO_ALL;
		if (!mHandler.hasMessages(what)) {
			mHandler.sendEmptyMessageDelayed(what, 1000);
		}
	}
	
	private void getAllInfo() {
		if (!mVehicleSessionValid || mQuiting || mPausing)
			return;
		
		if (!getFrontFog())
			return;
		
		if (!getRearFog())
			return;
		
		if (!getLeftLamp())
			return;
		
		if (!getRightLamp())
			return;
		
		if (!getDoors())
			return;		
	}
	
	private void doInit() {	
		LogUtils.LOGD(TAG, "doInit <----");			
		if (!mVehicleSessionValid && SocketTransportImp.Instance().isConnected()) {			
			mDiagnosticStart = new DiagnosticStart();
			mVehicleSessionValid = mDiagnosticStart.init();	
		}		
		keepAlive();
		
		LogUtils.LOGD(TAG, "doInit mVehicleSessionValid: " + mVehicleSessionValid + "--->");	
	}
	
	private void doUninit() {
		LogUtils.LOGD(TAG, "doUninit <---"); 		
		if (mGetInfo != null) {
			mGetInfo = null;
		}
		mVehicleSessionValid = false;
		LogUtils.LOGD(TAG, "doUninit --->"); 
	}
	
	private void notifyVehicleModel(int what, int arg1, int arg2, Object obj) {
		if (mModelHandler != null) {
			Message msg = new Message();
			msg.what = what;
			msg.arg1 = arg1;
			msg.arg2 = arg2;
			msg.obj = obj;
			mModelHandler.sendMessage(msg);
		}
	}
	
	private boolean getFrontFog() {	
		if (mGetInfo == null) {
			mGetInfo = new GetInfo();
		}	
	
		int nFailedCnt = 0;	
		byte[] tmp = new byte[1];
		do {
			tmp[0] = 0;
			if (mGetInfo.getFrontFog(tmp)) {
				Boolean res = (tmp[0] == 1)? Boolean.TRUE : Boolean.FALSE;
				notifyVehicleModel(RESPONSE_GET_INFO, IControl.EVENT_LAMP_FOG_FRONT_ALERT, 0, res);
				return true;
			}
			if (++nFailedCnt >= WARNING_COUNT) {
				notifyVehicleModel(RESPONSE_FAILED, 0, 0, null);
				return false;
			}
			
		} while(true);
	}
	
	private boolean getRearFog() {	
		if (mGetInfo == null) {
			mGetInfo = new GetInfo();
		}	
	
		int nFailedCnt = 0;	
		byte[] tmp = new byte[1];
		do {
			tmp[0] = 0;
			if (mGetInfo.getRearFog(tmp)) {
				Boolean res = (tmp[0] == 1)? Boolean.TRUE : Boolean.FALSE;
				notifyVehicleModel(RESPONSE_GET_INFO, IControl.EVENT_LAMP_FOG_BEHIND_ALERT, 0, res);
				return true;
			}
			if (++nFailedCnt >= WARNING_COUNT) {
				notifyVehicleModel(RESPONSE_FAILED, 0, 0, null);
				return false;
			}
			
		} while(true);
	}
	
	private boolean getLeftLamp() {	
		if (mGetInfo == null) {
			mGetInfo = new GetInfo();
		}	
	
		int nFailedCnt = 0;	
		byte[] tmp = new byte[1];
		do {
			tmp[0] = 0;
			if (mGetInfo.getLeftLamp(tmp)) {
				Boolean res = (tmp[0] == 1)? Boolean.TRUE : Boolean.FALSE;
				notifyVehicleModel(RESPONSE_GET_INFO, IControl.EVENT_ROTATE_LEFT_ALERT, 0, res);
				return true;
			}
			if (++nFailedCnt >= WARNING_COUNT) {
				notifyVehicleModel(RESPONSE_FAILED, 0, 0, null);
				return false;
			}
			
		} while(true);
	}
	
	private boolean getRightLamp() {	
		if (mGetInfo == null) {
			mGetInfo = new GetInfo();
		}	
	
		int nFailedCnt = 0;	
		byte[] tmp = new byte[1];
		do {
			tmp[0] = 0;
			if (mGetInfo.getRightLamp(tmp)) {
				Boolean res = (tmp[0] == 1)? Boolean.TRUE : Boolean.FALSE;
				notifyVehicleModel(RESPONSE_GET_INFO, IControl.EVENT_ROTATE_RIGHT_ALERT, 0, res);
				return true;
			}
			if (++nFailedCnt >= WARNING_COUNT) {
				notifyVehicleModel(RESPONSE_FAILED, 0, 0, null);
				return false;
			}
			
		} while(true);
	}
	
	private boolean getDoors() {	
		if (mGetInfo == null) {
			mGetInfo = new GetInfo();
		}	
			
		int step = 0;
		int nFailedCnt = 0;	
		boolean res = false;
		byte[] tmp = new byte[1];
		CarDoorState state = new CarDoorState();
		do {
			tmp[0] = 0;	
			switch (step) {
			case 0:
				if (res = mGetInfo.getFrontLeftDoor(tmp)) {
					state.mFrontLeftOpen = (tmp[0] == 1);	
					step++;				
				}
				break;
			case 1:
				if (res = mGetInfo.getFrontRightDoor(tmp)) {
					state.mFrontRightOpen = (tmp[0] == 1);	
					step++;				
				}
				break;
			case 2:
				if (res = mGetInfo.getRearLeftDoor(tmp)) {
					state.mBehindLeftOpen = (tmp[0] == 1);	
					step++;				
				}
				break;
			case 4:
				if (res = mGetInfo.getRearRightDoor(tmp)) {
					state.mBehindRightOpen = (tmp[0] == 1);	
					step++;				
				}
				break;
			default:
				res = true;
				break;
			}
			
			if (res && step >= 4) {
				break;
			}		
			else if (!res && ++nFailedCnt >= WARNING_COUNT) {
				notifyVehicleModel(RESPONSE_FAILED, 0, 0, null);
				return false;
			}
			
		} while(true);		
		
		notifyVehicleModel(RESPONSE_GET_INFO, IControl.EVENT_CAR_DOOR_CHANGE_ALERT, 0, state);
		return true;
	}
}
