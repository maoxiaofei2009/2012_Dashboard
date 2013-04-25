package uiOperation;

import org.ese.canId.CanIdType;
import org.ese.uds.UDS;
import org.ese.uds.UDSService;

import did.ReadBcm1;
import did.ReadBcm2;
import did.ReadDdcu;
import did.ReadPdcu;
import did.ReadRldcu;
import did.ReadRrdcu;
//每条发送命令，都要设置超时机制
//如果返回错误，则弹出toast“命令执行失败”
public class SetCommand {
	UDSService uds = new UDSService();
	
	public boolean setFrontFog(byte value){//true:执行成功 false：执行失败
		// session 转换:进入扩展session 
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x03, (byte)0)) != UDS.OK){
			return false;//判断返回值
		} 
		
		if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM1, did.InputOutputBcm1.frontLeftFog, value)) != UDS.OK){
			return false;//判断返回值
		}
		if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM1, did.InputOutputBcm1.frontRightFog, value)) !=  UDS.OK){
			return false;//判断返回值 
		}
		//TODO 退出扩展session
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x00, (byte)0)) != UDS.OK){
			return false;//判断返回值 
		}
		return true;
	}
	
	public boolean setRearFog(byte value){//true:执行成功 false：执行失败
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x03, (byte)0)) != UDS.OK){
			return false;//判断返回值 
		}
		if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM2, did.InputOutputBcm2.rearLeftFog, value)) != UDS.OK){
			return false;//判断返回值 
		}
		if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM2, did.InputOutputBcm2.rearRightFog, value)) != UDS.OK){
			return false;//判断返回值 
		}

		//TODO 退出扩展session
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x00, (byte)0)) != UDS.OK){
			return false;//判断返回值 
		}
		return true;

	}
	
	public boolean setLeftLamp(byte value){//true:执行成功 false：执行失败
		//	UDSService uds = new UDSService();
			
			//FrontLeft lamp
			// session 转换:进入扩展session 
			if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x03, (byte)0)) != UDS.OK){
				return false;//判断返回值 
			}
			if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM1, did.InputOutputBcm1.frontLeftLamp, value)) != UDS.OK);//判断返回值 
			//TODO 退出扩展session
			if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x00, (byte)0)) != UDS.OK){
				return false;//判断返回值 
			}

			//RearLeft Lamp
			// session 转换:进入扩展session 
			if(( uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x03, (byte)0)) != UDS.OK){
				return false;//判断返回值 
			}
			if(( uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM2, ReadBcm2.rearLeftLamp, value)) != UDS.OK){
				return false;//判断返回值 
			}
			//TODO 退出扩展session
			if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x00, (byte)0)) != UDS.OK){
				return false;//判断返回值 
			}

			return true;
		}



	
	public boolean setRightLamp(byte value){//true:执行成功 false：执行失败
	//	UDSService uds = new UDSService();

		// session 转换:进入扩展session 
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x03, (byte)0)) != UDS.OK){
			return false;//判断返回值 
		}
		if(( uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM1, ReadBcm1.frontRightLamp, value)) != UDS.OK){
			return false;//判断返回值  
		}
		//TODO 退出扩展session
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x00, (byte)0)) != UDS.OK){
			return false;//判断返回值  
		}

		// session 转换:进入扩展session 
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x03, (byte)0)) != UDS.OK){
			return false;//判断返回值  
		}
		if(( uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM2, ReadBcm2.rearRightLamp, value)) != UDS.OK){
			return false;//判断返回值  
		}
		//TODO 退出扩展session
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x00, (byte)0)) != UDS.OK){
			return false;//判断返回值  
		}

		return true;
	}
	

	
	public boolean setFrontLeftWindow(byte value){//true:执行成功 false：执行失败
	if(( uds.InputOutputContrlByIdentifier(CanIdType.Tester2DDCU, ReadDdcu.frontLeftWindow, value)) != UDS.OK){
			return false;//判断返回值
		}
		return true;
	}
	
	public boolean setFrontRightWindow(byte value){//true:执行成功 false：执行失败
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2PDCU, ReadPdcu.frontRightWindow, value)) != UDS.OK){
			return false;//判断返回值  
		}
		return true;
	}
	
	public boolean setRearLeftWindow(byte value){//true:执行成功 false：执行失败
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2RLDCU, ReadRldcu.rearLeftWindow, value)) != UDS.OK){
			return false;//判断返回值  
		}
		return true;
	}
	
	public boolean setRearRightWindow(byte value){//true:执行成功 false：执行失败
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2RRDCU, ReadRrdcu.rearRightWindow, value)) != UDS.OK){
			return false;//判断返回值  
		}
		return true;
	}
	
	
	public boolean setFrontLeftDoor(byte value){//true:执行成功 false：执行失败
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2DDCU, ReadDdcu.frontLeftDoor, value)) != UDS.OK){
			return false;//判断返回值  
		}
		return true;
	}
	
	public boolean setFrontRightDoor(byte value){//true:执行成功 false：执行失败
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2PDCU, ReadPdcu.frontRightDoor, value)) != UDS.OK){
			return false;//判断返回值  
		}
		return true;
	}
	
	public boolean setRearLeftDoor(byte value){//true:执行成功 false：执行失败
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2RLDCU, ReadRldcu.rearLeftDoor, value)) != UDS.OK){
			return false;//判断返回值  
		}
		return true;
	}
	
	public boolean setRearRightDoor(byte value){//true:执行成功 false：执行失败
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2RRDCU, ReadRrdcu.rearRightDoor, value)) != UDS.OK){
			return false;//判断返回值  
		}
		return true;
	}
	
	


}
