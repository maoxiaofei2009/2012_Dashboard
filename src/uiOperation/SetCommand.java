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
//ÿ�����������Ҫ���ó�ʱ����
//������ش����򵯳�toast������ִ��ʧ�ܡ�
public class SetCommand {
	UDSService uds = new UDSService();
	
	public boolean setFrontFog(byte value){//true:ִ�гɹ� false��ִ��ʧ��
		// session ת��:������չsession 
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x03, (byte)0)) != UDS.OK){
			return false;//�жϷ���ֵ
		} 
		
		if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM1, did.InputOutputBcm1.frontLeftFog, value)) != UDS.OK){
			return false;//�жϷ���ֵ
		}
		if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM1, did.InputOutputBcm1.frontRightFog, value)) !=  UDS.OK){
			return false;//�жϷ���ֵ 
		}
		//TODO �˳���չsession
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x00, (byte)0)) != UDS.OK){
			return false;//�жϷ���ֵ 
		}
		return true;
	}
	
	public boolean setRearFog(byte value){//true:ִ�гɹ� false��ִ��ʧ��
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x03, (byte)0)) != UDS.OK){
			return false;//�жϷ���ֵ 
		}
		if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM2, did.InputOutputBcm2.rearLeftFog, value)) != UDS.OK){
			return false;//�жϷ���ֵ 
		}
		if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM2, did.InputOutputBcm2.rearRightFog, value)) != UDS.OK){
			return false;//�жϷ���ֵ 
		}

		//TODO �˳���չsession
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x00, (byte)0)) != UDS.OK){
			return false;//�жϷ���ֵ 
		}
		return true;

	}
	
	public boolean setLeftLamp(byte value){//true:ִ�гɹ� false��ִ��ʧ��
		//	UDSService uds = new UDSService();
			
			//FrontLeft lamp
			// session ת��:������չsession 
			if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x03, (byte)0)) != UDS.OK){
				return false;//�жϷ���ֵ 
			}
			if((uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM1, did.InputOutputBcm1.frontLeftLamp, value)) != UDS.OK);//�жϷ���ֵ 
			//TODO �˳���չsession
			if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x00, (byte)0)) != UDS.OK){
				return false;//�жϷ���ֵ 
			}

			//RearLeft Lamp
			// session ת��:������չsession 
			if(( uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x03, (byte)0)) != UDS.OK){
				return false;//�жϷ���ֵ 
			}
			if(( uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM2, ReadBcm2.rearLeftLamp, value)) != UDS.OK){
				return false;//�жϷ���ֵ 
			}
			//TODO �˳���չsession
			if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x00, (byte)0)) != UDS.OK){
				return false;//�жϷ���ֵ 
			}

			return true;
		}



	
	public boolean setRightLamp(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	//	UDSService uds = new UDSService();

		// session ת��:������չsession 
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x03, (byte)0)) != UDS.OK){
			return false;//�жϷ���ֵ 
		}
		if(( uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM1, ReadBcm1.frontRightLamp, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		//TODO �˳���չsession
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM1, (byte)0x00, (byte)0)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}

		// session ת��:������չsession 
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x03, (byte)0)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		if(( uds.InputOutputContrlByIdentifier(CanIdType.Tester_To_BCM2, ReadBcm2.rearRightLamp, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		//TODO �˳���չsession
		if((uds.DiagnosticSessionControl(CanIdType.Tester_To_BCM2, (byte)0x00, (byte)0)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}

		return true;
	}
	

	
	public boolean setFrontLeftWindow(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	if(( uds.InputOutputContrlByIdentifier(CanIdType.Tester2DDCU, ReadDdcu.frontLeftWindow, value)) != UDS.OK){
			return false;//�жϷ���ֵ
		}
		return true;
	}
	
	public boolean setFrontRightWindow(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2PDCU, ReadPdcu.frontRightWindow, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		return true;
	}
	
	public boolean setRearLeftWindow(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2RLDCU, ReadRldcu.rearLeftWindow, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		return true;
	}
	
	public boolean setRearRightWindow(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2RRDCU, ReadRrdcu.rearRightWindow, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		return true;
	}
	
	
	public boolean setFrontLeftDoor(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2DDCU, ReadDdcu.frontLeftDoor, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		return true;
	}
	
	public boolean setFrontRightDoor(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2PDCU, ReadPdcu.frontRightDoor, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		return true;
	}
	
	public boolean setRearLeftDoor(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2RLDCU, ReadRldcu.rearLeftDoor, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		return true;
	}
	
	public boolean setRearRightDoor(byte value){//true:ִ�гɹ� false��ִ��ʧ��
	if((uds.InputOutputContrlByIdentifier(CanIdType.Tester2RRDCU, ReadRrdcu.rearRightDoor, value)) != UDS.OK){
			return false;//�жϷ���ֵ  
		}
		return true;
	}
	
	


}
