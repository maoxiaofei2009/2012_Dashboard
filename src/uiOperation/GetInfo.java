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
//�����еĺ���������true�����������е���ֵ���ã�����false�����ú���ִ��ʧ�ܡ���Ҫ���»�ȡ���ݡ�
//UI�У�������»�ȡ����3�κ�ʧ�ܣ�����toast����ȡ����ʧ�ܡ�
public class GetInfo {
	public UDSService uds = new UDSService();
	
	public boolean getFrontFog(byte[] tmp){//tmp����0Ϊoff������1Ϊon
		int status = uds.ReadData(CanIdType.Tester_To_BCM1, ReadBcm1.frontFog, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearFog(byte[] tmp){//tmp����0Ϊoff������1Ϊon
		int status = uds.ReadData(CanIdType.Tester_To_BCM2, ReadBcm2.rearFog, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}

	public boolean getLeftLamp(byte[] tmp){//tmp����0Ϊoff������1Ϊon
		int statusFront = uds.ReadData(CanIdType.Tester_To_BCM1, ReadBcm1.frontLeftLamp, tmp);//�жϷ���ֵ 
//		int statusRear = uds.ReadData(CanIdType.Tester_To_BCM2, ReadBcm2.rearLeftLamp, tmp);//�жϷ���ֵ 
		
		if(statusFront != UDS.OK ){
			return false;
		}else{
			return true;
		}
	}
	
	
	public boolean getRightLamp(byte[] tmp){//tmp����0Ϊoff������1Ϊon
		int statusFront = uds.ReadData(CanIdType.Tester_To_BCM1, ReadBcm1.frontRightLamp, tmp);//�жϷ���ֵ 
//		int statusRear = uds.ReadData(CanIdType.Tester_To_BCM2, ReadBcm2.rearRightLamp, tmp);//�жϷ���ֵ 

		if(statusFront != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	

	
	public boolean getFrontLeftWindow(byte[] tmp){//tmp����   0:No action��1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2DDCU, ReadDdcu.frontLeftWindow, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getFrontRightWindow(byte[] tmp){//tmp����   0:No action��1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2PDCU, ReadPdcu.frontRightWindow, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearLeftWindow(byte[] tmp){//tmp����   0:No action��1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2RLDCU, ReadRldcu.rearLeftWindow, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearRightWindow(byte[] tmp){//tmp����   0:No action��1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2RRDCU, ReadRrdcu.rearRightWindow, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	
	public boolean getFrontLeftDoor(byte[] tmp){//tmp����0Ϊclose������1Ϊopen
		int status = uds.ReadData(CanIdType.Tester2DDCU, ReadDdcu.frontLeftDoor, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getFrontRightDoor(byte[] tmp){//tmp����0Ϊclose������1Ϊopen
		int status = uds.ReadData(CanIdType.Tester2PDCU, ReadPdcu.frontRightDoor, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearLeftDoor(byte[] tmp){//tmp����0Ϊclose������1Ϊopen
		int status = uds.ReadData(CanIdType.Tester2RLDCU, ReadRldcu.rearLeftDoor, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearRightDoor(byte[] tmp){//tmp//tmp����   0:No action��1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2RRDCU, ReadRrdcu.rearRightDoor, tmp);//�жϷ���ֵ 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	
}
