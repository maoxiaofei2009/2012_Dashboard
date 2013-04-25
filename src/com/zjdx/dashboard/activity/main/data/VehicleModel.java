package com.zjdx.dashboard.activity.main.data;

import com.zjdx.dashboard.activity.main.control.IControl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * VehicleModel ����������ݵ�����,��UI���»�λ���н��ߡ�
 * ��ͨ��VehicleThreadHandler����ʵ������λ��ͨ�ŵĹ��ܡ�
 * VehicleThreadHandler��ͨ�Ž��������������Ϣ����Handler��Ϣ���ƣ�mUIMainHandler��
 * ����VehicleModel���μ�handleMessage(Message msg).
 * IControl ��UI��interface, VehicleModelͨ������UI������
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
