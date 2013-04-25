package org.ese.uds;

public class UDS
{
	public static int OK = 0;
	public static int ERROR_UNKOWN = -1;
	public static int ERROR_TIMEOUT = -2;
	
	public static int ReponseTimeOut = 0x32;

	public static byte SVR_SESSION_CONTROL = 0x10;
	public static byte SVR_SESSION_CONTROL_RES = 0x50;
	

	public static byte SVR_ECU_RESET = 0x11;
	public static byte SVR_ECU_RESET_RES = 0x51;

	public static byte SVR_SECURITY_ACCESS = 0x27;
	public static byte SVR_SECURITY_ACCESS_RES = 0x67;
	public static byte SVC_SECURITY_ACCESS_SEED_SIZE = 0x04;

	public static byte SVR_ROUTE_CONTROL = 0x31;
	public static byte SVR_ROUTE_CONTROL_RES = 0x71;

	public static byte SVR_REQUEST_DOWNLOAD = 0x34;
	public static byte SVR_REQUEST_DOWNLOAD_RES = 0x50;

	public static byte SVR_REQUEST_UPLOAD = 0x35;
	public static byte SVR_REQUEST_UPLOAD_RES = 0x35;

	public static byte SVR_TRANSFER_DATA = 0x36;
	public static byte SVR_REQUEST_TRANSFER_EXIT = 0x37;
	public static byte SVR_REQUEST_TRANSFER_EXIT_RES = 0x77;

	public static byte SVR_TSETRT_PRESENT = 0x3E;
	public static byte SVR_TSETRT_PRESENT_RES = 0x7E;

	public static byte SVR_COMMUNICATION_CONTROL = 0x28;
	public static byte SVR_COMMUNICATION_CONTROL_RES = 0x68;

	public static byte SVR_DTC_CONTROL = (byte) 0x85;
	public static byte SVR_DTC_CONTROL_RES = (byte) 0xC5;

	public static byte SVR_WRITE_DATA = 0x2E; 
	public static byte SVR_WRITE_DATA_RES = 0x6E;
	
	public static byte SVR_READ_DATA = 0x22; 
	public static byte SVR_REAS_DATA_RES = 0x62;
	
	public static byte CVR_INPUT_OUTPUT_DATA = 0x2f;
	public static byte CVR_INPUT_OUTPUT_DATA_RES = 0x6f;	
}
