package com.smartsar.uds.network;

import com.zjdx.dashboard.activity.main.data.SocketTransportImp;


public class SocketTransport {
	
	public static ReturnType SendSocket(byte[] socket){
		return SocketTransportImp.Instance().write(socket)? ReturnType.E_OK : ReturnType.E_NOT_OK;		
	}
	
	public static byte[] ReceiveSocket(){
		return SocketTransportImp.Instance().read();
	}
}
