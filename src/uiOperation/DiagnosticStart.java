package uiOperation;

import org.ese.canId.CanIdType;
import org.ese.uds.UDS;
import org.ese.uds.UDSService;

import android.util.Log;

public class DiagnosticStart {	
	private static final String TAG = "DiagnosticStart ";
	private volatile boolean mQuiting = false; 
	
	UDSService mUdsService;
	//call DiagnosticSessionControl
	public DiagnosticStart() {
		mUdsService =  new UDSService();		
	}
	
	public boolean init() {
		Log.d(TAG, "init <---");
		boolean res = false;
		do {
			while(!isQuiting() && (mUdsService.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x00, (byte)0)) != UDS.OK);//判断返回值 
			while(!isQuiting() && (mUdsService.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x00, (byte)0)) != UDS.OK);//判断返回值 
			while(!isQuiting() && (mUdsService.DiagnosticSessionControl(CanIdType.Tester2DDCU, (byte)0x00, (byte)0)) != UDS.OK);//判断返回值 
			while(!isQuiting() && (mUdsService.DiagnosticSessionControl(CanIdType.Tester2PDCU, (byte)0x00, (byte)0)) != UDS.OK);//判断返回值 
			while(!isQuiting() && (mUdsService.DiagnosticSessionControl(CanIdType.Tester2RRDCU, (byte)0x00, (byte)0)) != UDS.OK);//判断返回值 
			while(!isQuiting() && (mUdsService.DiagnosticSessionControl(CanIdType.Tester2RLDCU, (byte)0x00, (byte)0)) != UDS.OK);//判断返回值
			res = !isQuiting();
		} while(false);
		Log.d(TAG, "init --->");
		return res;
	}
	
	public void tryQuit() {
		Log.i(TAG, "tryQuit...");
		mQuiting = true;
	}
	
	private boolean isQuiting() {
		return mQuiting;		
	}

}
