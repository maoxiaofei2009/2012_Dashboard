package com.zjdx.dashboard.activity.main.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.zjdx.dashboard.LogUtils;

/**
 * SocketTransportImp 是SocketTransport的实现。
 * 以singleton方式供外面例用。
 * 它通过NetConfigure获取网络参数，创建TCP/IP Socket.
 * 内部通过checkState来检测服务器是否可达。
 * 
 */

public class SocketTransportImp {
	private static final String TAG = "SocketTransportImp ";
	
	private static SocketTransportImp mInstatnce = null;
	private Socket mSocket = null;
	private byte[] mBuffer = new byte[4096];
	private NetConfigure mConfigure = null;	
	private String mCfgFilePath = "vehicle.cfg";
	
	public static synchronized SocketTransportImp Instance() {
		if (mInstatnce == null) {
			mInstatnce = new SocketTransportImp();
		}
		return mInstatnce;
	}
	
	public synchronized boolean isConnected() {
		open();	
		return checkState();
	}
	
	public synchronized boolean write(byte[] data){
		boolean res = false;
		LogUtils.LOGD(TAG, "write <---");
		if (!checkState()) {
			open();	
		}
		
		if (data != null && mSocket != null && mSocket.isConnected()) {				
			try {
				OutputStream out = mSocket.getOutputStream();
				out.write(data);
				out.flush();
				res = true;
			} catch (IOException e) {
				LogUtils.LOGE(TAG, "write IOException");
//				e.printStackTrace();
			}				
		}
		LogUtils.LOGD(TAG, "write frame res= " + res + " --->");
		
		return res;
	}
	
	public synchronized byte[] read(){
		LogUtils.LOGD(TAG, "read <---");
		byte[] buffer = null;
		
		open();
		if (mSocket != null && mSocket.isConnected()) {
			try {
				InputStream in = mSocket.getInputStream();	
				
				int len = in.read(mBuffer);
				if (len > 0) {
					buffer = new byte[len];
					System.arraycopy(mBuffer, 0, buffer, 0, len);
					LogUtils.LOGD(TAG, "receive string ok:  " + len);										
				}
			} catch (IOException e) {
				LogUtils.LOGE(TAG, "read IOException");
//				e.printStackTrace();
			}				
		}
		LogUtils.LOGD(TAG, "read --->");
		return buffer;
	}
	
	private synchronized void open() {
		LogUtils.LOGD(TAG, "open <----");
		try {
			if (mSocket != null && mSocket.isClosed()) {
				mSocket = null;
			}
			
			if (mSocket == null) {
				LogUtils.LOGD(TAG, "open");
				mConfigure = new NetConfigure(mCfgFilePath);
				InetSocketAddress address = new InetSocketAddress(mConfigure.getServerAddress(), mConfigure.getServerPort());
//				mSocket = new Socket(mConfigure.getServerAddress(), mConfigure.getServerPort());
				
				mSocket = new Socket();
				mSocket.connect(address, 3000);
				mSocket.setKeepAlive(true);
				mSocket.setTcpNoDelay(true);
				mSocket.setSoTimeout(mConfigure.getTimeOut());				
			} 				
		} catch (IllegalArgumentException e) {
			LogUtils.LOGE(TAG, "open IllegalArgumentException");
			close();
		} catch (UnknownHostException e) {
			LogUtils.LOGE(TAG, "open UnknownHostException");
			close();
//			e.printStackTrace();
		} catch (IOException e) {
			LogUtils.LOGE(TAG, "open IOException");
			close();
//			e.printStackTrace();
		}
		LogUtils.LOGD(TAG, "open ---->");
	}
	
	private synchronized void close() {
		if (mSocket != null) {
			try {
				LogUtils.LOGD(TAG, "close");
				mSocket.close();
				mSocket = null;
			} catch (IOException e) {
				mSocket = null;
				LogUtils.LOGE(TAG, "close IOException");
//				e.printStackTrace();
			}
		}
	}
	
	private synchronized boolean checkState() {
		LogUtils.LOGD(TAG, "checkState <----");
		boolean res = false;
		if (mSocket != null && mSocket.isConnected()) {	
			try {
				mSocket.setOOBInline(false);
				mSocket.sendUrgentData(0xFF);
				res = true;
			} catch (IOException e) {
				LogUtils.LOGE(TAG, "checkState IOException");
				e.printStackTrace();
				close();
			}
		}	
		LogUtils.LOGD(TAG, "checkState " + res + " ---->");
		return res;
	}
	
	private SocketTransportImp() {
		
	}
}
