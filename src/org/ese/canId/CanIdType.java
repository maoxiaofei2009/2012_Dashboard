package org.ese.canId;


public class CanIdType {
	public static byte CanFrameType = 0x08;
	public static byte FirstResponseData = 0x06;
	public static byte SessionControlDl = 0x02;
	public static byte TesterPresentDl = 0x02;
	public static byte SecurityAccessS1Dl = 0x02;
	public static byte SecurityAccessS2Dl = 0x06;
	public static byte WriteDataDl = 0x04;
	public static byte ReadDataDl = 0x03;
	public static byte InputOutputDataDL = 0x05;
	
	public static int FuncAddrFromTester = 0x7DF;
	
	public static int Tester2DDCU = 0x759;
	public static int DDCU2Tester = 0x779;
	public static int Tester2PDCU = 0x75A;
	public static int PDCU2Tester = 0x77A;
	public static int Tester2RLDCU = 0x75B;
	public static int RLDCU2Tester = 0x77B;
	public static int Tester2RRDCU = 0x75C;
	public static int RRDCU2Tester = 0x77C;
	
	public static int Tester_To_BCM1 = 0x75E;
	public static int BCM1_To_Tester = 0x77E;
	public static int Tester_To_BCM2 = 0x75D;
	public static int BCM2_To_Tester = 0x77D;
    


}
