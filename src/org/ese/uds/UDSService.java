package org.ese.uds;


import org.ese.canId.CanIdType;


import com.smartsar.uds.network.ByteOrder;
import com.smartsar.uds.network.Packet;
import com.smartsar.uds.network.SocketTransport;
public class UDSService {
//	private CANTransportLayer transportLayer;
    public static Packet SessionControlFrame = new Packet(ByteOrder.Motorola, 13);
    public static Packet TesterPresentFrame = new Packet(ByteOrder.Motorola, 13);
    public static Packet SecurityAccessFrame = new Packet(ByteOrder.Motorola, 13);
    public static Packet ReadDataFrame = new Packet(ByteOrder.Motorola, 13);
    public static Packet InputOutputFrame = new Packet(ByteOrder.Motorola, 13);
    
    
//	private int targetAddress;
	public static void  setOverTime(int time){
		//目前采用延时time ms TODO
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int DiagnosticSessionControl(int canId, byte sessionType, byte responseReq)
	{	
		responseReq = (byte)0x0;	//responseReq 必须为1
		{
			SessionControlFrame.putByte(0, CanIdType.CanFrameType) ;
			SessionControlFrame.putDWord(1, canId)  ;
			SessionControlFrame.putByte(5, CanIdType.SessionControlDl);
			SessionControlFrame.putByte(6, UDS.SVR_SESSION_CONTROL);
			SessionControlFrame.putByte(7, (byte)(sessionType | (responseReq << 7)));
			SessionControlFrame.getFramePading(8);			
			SocketTransport.SendSocket(ReadDataFrame.getData());//TODO,需要发送该socket
		}
		{
			setOverTime(UDS.ReponseTimeOut);//延时0x32ms。
			byte[] socketData = SocketTransport.ReceiveSocket();
			Packet response = new Packet(ByteOrder.Motorola, socketData);	
			if (response.getData() != null)
			{
				if (response.getByte(CanIdType.FirstResponseData) == 0x7F)
				{
					return response.getByte(CanIdType.FirstResponseData+2); //返回NRC
				}
				if (response.getByte(CanIdType.FirstResponseData)!= UDS.SVR_SESSION_CONTROL_RES)
				{
					return UDS.ERROR_UNKOWN;
				}
				return UDS.OK;
			}
			return UDS.ERROR_UNKOWN;
		}
	}



	public int TesterPresent(int canId, byte ZeroSubFunction)
	{

		{			
			TesterPresentFrame.putByte(0, CanIdType.CanFrameType) ;
			TesterPresentFrame.putDWord(1, canId)  ;
			TesterPresentFrame.putByte(5, CanIdType.TesterPresentDl);
			TesterPresentFrame.putByte(6, UDS.SVR_TSETRT_PRESENT);
			TesterPresentFrame.putByte(7, ZeroSubFunction);
			TesterPresentFrame.getFramePading(8);			
			SocketTransport.SendSocket(ReadDataFrame.getData());//TODO,需要发送该socket		
		}

		{
			setOverTime(UDS.ReponseTimeOut);
			byte[] socketData =SocketTransport.ReceiveSocket();//TODO
			Packet response = new Packet(ByteOrder.Motorola, socketData);
			if (response.getData() != null)
			{
				if (response.getByte(CanIdType.FirstResponseData) == 0x7F)
				{
					return response.getByte(CanIdType.FirstResponseData+2); //返回NRC
				}
				if (response.getByte(CanIdType.FirstResponseData) != UDS.SVR_TSETRT_PRESENT_RES)
				{
					return UDS.ERROR_UNKOWN;
				}
				return UDS.OK;
			}
			return UDS.ERROR_UNKOWN;			
		}
	}

	// / <summary>
	// / 获得校验计算的Seed
	// / </summary>
	// / <returns></returns>
	public Object SecurityAccess(int canId)
	{
		{			
			SecurityAccessFrame.putByte(0, CanIdType.CanFrameType) ;
			SecurityAccessFrame.putDWord(1, canId)  ;
			SecurityAccessFrame.putByte(5, CanIdType.SecurityAccessS1Dl);
			SecurityAccessFrame.putByte(6, UDS.SVR_SECURITY_ACCESS);
			SecurityAccessFrame.putByte(7, 0x01);
			SecurityAccessFrame.getFramePading(8);			
			SocketTransport.SendSocket(ReadDataFrame.getData());//TODO,需要发送该socket		
		}

		{
			setOverTime(UDS.ReponseTimeOut);
			byte[] socketData = SocketTransport.ReceiveSocket();//TODO
			Packet response = new Packet(ByteOrder.Motorola, socketData);

			if (response.getData() != null)
			{
				if (response.getByte(CanIdType.FirstResponseData) == 0x7F)
				{
					return null;
				}
				if (response.getByte(CanIdType.FirstResponseData) != UDS.SVR_SECURITY_ACCESS_RES &&
						response.getByte(CanIdType.FirstResponseData+1) != 0x01)//positive response step 1
				{
					return null;
				}

				int seed =response.getDWord(CanIdType.FirstResponseData+2);				
				return seed;
			}			
		}
		return null;
	}

	// / <summary>
	// / 根据提供的密钥进行安全验证
	// / </summary>
	// / <param name="seed">Seed</param>
	// / <param name="key1"></param>
	// / <param name="key2"></param>
	// / <returns></returns>
	public int SecurityAccess(int canId,  byte[] seed, String key1, String key2)
	{
		{	
			int key = 0;//des.Encrypt3DES(seed, key1, key2);//get the key,TODO
			SecurityAccessFrame.putByte(0, CanIdType.CanFrameType) ;
			SecurityAccessFrame.putDWord(1, canId)  ;
			SecurityAccessFrame.putByte(5, CanIdType.SecurityAccessS2Dl);
			SecurityAccessFrame.putByte(6, UDS.SVR_SECURITY_ACCESS);
			SecurityAccessFrame.putByte(7, 0x02);
			SecurityAccessFrame.putDWord(8, key);
			SecurityAccessFrame.getFramePading(12);			
			SocketTransport.SendSocket(ReadDataFrame.getData());//TODO,需要发送该socket		
		}
		
		{
			setOverTime(UDS.ReponseTimeOut);
			byte[] socketData =SocketTransport.ReceiveSocket();//TODO
			Packet response = new Packet(ByteOrder.Motorola, socketData);
			if (response.getData() != null)
			{
				if (response.getByte(CanIdType.FirstResponseData) == 0x7F)
				{
					return response.getByte(CanIdType.FirstResponseData+2); //返回NRC
				}
				if (response.getByte(CanIdType.FirstResponseData) != UDS.SVR_SECURITY_ACCESS_RES ||
						response.getByte(CanIdType.FirstResponseData+1) != 0x02)//positive response step 2
				{
					return UDS.ERROR_UNKOWN;
				}
				return UDS.OK;
			}
		}
		return UDS.ERROR_UNKOWN;
	}

	

	
	public int ReadData(int canId, short did, byte[] status)
	{
		{	
			ReadDataFrame.putByte(0, CanIdType.CanFrameType) ;
			ReadDataFrame.putDWord(1, canId)  ;
			ReadDataFrame.putByte(5, CanIdType.ReadDataDl);
			ReadDataFrame.putByte(6, UDS.SVR_READ_DATA);
			ReadDataFrame.putWord(7, did);
			ReadDataFrame.getFramePading(9);			
			SocketTransport.SendSocket(ReadDataFrame.getData());//TODO,需要发送该socket		
		}
		
		{
			setOverTime(UDS.ReponseTimeOut);
			byte[] socketData = SocketTransport.ReceiveSocket();//TODO
			Packet response = new Packet(ByteOrder.Motorola, socketData);

			if (response.getData() != null)
			{
				if (response.getByte(CanIdType.FirstResponseData) == 0x7F)
				{
					return response.getByte(CanIdType.FirstResponseData+2); //返回NRC
				}
				if (response.getByte(CanIdType.FirstResponseData) != UDS.SVR_REAS_DATA_RES ||
						response.getWord(CanIdType.FirstResponseData + 1) != did)//positive response
				{
					return UDS.ERROR_UNKOWN;
				}
				status[0] = response.getByte(CanIdType.FirstResponseData+3);
				return UDS.OK;
			}
		}
		return UDS.ERROR_UNKOWN;
	}
	
	public int InputOutputContrlByIdentifier(int canId, short did, byte status)
	{
		
		{	
			InputOutputFrame.putByte(0, CanIdType.CanFrameType) ;
			InputOutputFrame.putDWord(1, canId)  ;
			InputOutputFrame.putByte(5, CanIdType.InputOutputDataDL);
			InputOutputFrame.putByte(6, UDS.CVR_INPUT_OUTPUT_DATA);
			InputOutputFrame.putWord(7, did);
			InputOutputFrame.putWord(8, 0x03);//shortTermAdjustment	TODO, 是否发送0x00呢？
			InputOutputFrame.putWord(9, status);			
			InputOutputFrame.getFramePading(10);			
			SocketTransport.SendSocket(ReadDataFrame.getData());//TODO,需要发送该socket				
		}

		{
			setOverTime(UDS.ReponseTimeOut);
			byte[] socketData = new byte[13];//TODO
			Packet response = new Packet(ByteOrder.Motorola, socketData);

			if (response.getData() != null)
			{
				if (response.getByte(CanIdType.FirstResponseData) == 0x7F)
				{
					return response.getByte(CanIdType.FirstResponseData+2); //返回NRC
				}
				if (response.getByte(CanIdType.FirstResponseData) != UDS.CVR_INPUT_OUTPUT_DATA_RES ||
						response.getWord(CanIdType.FirstResponseData + 1) != did)//positive response
				{
					return UDS.ERROR_UNKOWN;
				}
				status = response.getByte(CanIdType.FirstResponseData+3);
				return UDS.OK;
			}
		}
		return UDS.ERROR_UNKOWN;
	}
}

	