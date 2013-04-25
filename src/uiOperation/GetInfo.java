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
//该类中的函数，返回true代表传出参数中的数值可用；返回false则代表该函数执行失败。需要重新获取数据。
//UI中，如果重新获取数据3次后失败，弹出toast“获取数据失败”
public class GetInfo {
	public UDSService uds = new UDSService();
	
	public boolean getFrontFog(byte[] tmp){//tmp返回0为off，返回1为on
		int status = uds.ReadData(CanIdType.Tester_To_BCM1, ReadBcm1.frontFog, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearFog(byte[] tmp){//tmp返回0为off，返回1为on
		int status = uds.ReadData(CanIdType.Tester_To_BCM2, ReadBcm2.rearFog, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}

	public boolean getLeftLamp(byte[] tmp){//tmp返回0为off，返回1为on
		int statusFront = uds.ReadData(CanIdType.Tester_To_BCM1, ReadBcm1.frontLeftLamp, tmp);//判断返回值 
//		int statusRear = uds.ReadData(CanIdType.Tester_To_BCM2, ReadBcm2.rearLeftLamp, tmp);//判断返回值 
		
		if(statusFront != UDS.OK ){
			return false;
		}else{
			return true;
		}
	}
	
	
	public boolean getRightLamp(byte[] tmp){//tmp返回0为off，返回1为on
		int statusFront = uds.ReadData(CanIdType.Tester_To_BCM1, ReadBcm1.frontRightLamp, tmp);//判断返回值 
//		int statusRear = uds.ReadData(CanIdType.Tester_To_BCM2, ReadBcm2.rearRightLamp, tmp);//判断返回值 

		if(statusFront != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	

	
	public boolean getFrontLeftWindow(byte[] tmp){//tmp返回   0:No action，1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2DDCU, ReadDdcu.frontLeftWindow, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getFrontRightWindow(byte[] tmp){//tmp返回   0:No action，1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2PDCU, ReadPdcu.frontRightWindow, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearLeftWindow(byte[] tmp){//tmp返回   0:No action，1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2RLDCU, ReadRldcu.rearLeftWindow, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearRightWindow(byte[] tmp){//tmp返回   0:No action，1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2RRDCU, ReadRrdcu.rearRightWindow, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	
	public boolean getFrontLeftDoor(byte[] tmp){//tmp返回0为close，返回1为open
		int status = uds.ReadData(CanIdType.Tester2DDCU, ReadDdcu.frontLeftDoor, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getFrontRightDoor(byte[] tmp){//tmp返回0为close，返回1为open
		int status = uds.ReadData(CanIdType.Tester2PDCU, ReadPdcu.frontRightDoor, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearLeftDoor(byte[] tmp){//tmp返回0为close，返回1为open
		int status = uds.ReadData(CanIdType.Tester2RLDCU, ReadRldcu.rearLeftDoor, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getRearRightDoor(byte[] tmp){//tmp//tmp返回   0:No action，1:up,2:down,3 reserved
		int status = uds.ReadData(CanIdType.Tester2RRDCU, ReadRrdcu.rearRightDoor, tmp);//判断返回值 
		if(status != UDS.OK){
			return false;
		}else{
			return true;
		}
	}
	
	
}
