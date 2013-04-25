package com.smartsar.uds.network;

public class Packet
{

	private ByteOrder byteOrder = ByteOrder.Motorola;

	private byte[] data;

	public ByteOrder getByteOrder()
	{
		return byteOrder;
	}

	public void setByteOrder(ByteOrder byteOrder)
	{
		this.byteOrder = byteOrder;
	}

	public byte[] getData()
	{
		return data;
	}

	public void setData(byte[] data)
	{
		this.data = data;
	}

	public Packet(ByteOrder byteOrder)
	{
		this.byteOrder = byteOrder;
		data = new byte[8];
	}

	public Packet(ByteOrder byteOrder, int length)
	{
		this.byteOrder = byteOrder;
		data = new byte[length];
	}

	public Packet(ByteOrder byteOrder, byte[] data)
	{
		this.byteOrder = byteOrder;
		this.data = data;
	}

	public byte getByte(int index)
	{
		return data[index];
	}

	public byte getSByte(int index)
	{
		return (byte) data[index];
	}

	public int getWord(int index)
	{
		if (byteOrder == ByteOrder.Motorola)
		{
			return (int) (((int) data[index]) << 8 | ((int) data[index + 1]));
		} else
		{
			return (int) (((int) data[index + 1]) << 8 | ((int) data[index]));
		}
	}

	public short getSWord(int index)
	{
		if (byteOrder == ByteOrder.Motorola)
		{
			return (short) (((int) data[index]) << 8 | ((int) data[index + 1]));
		} else
		{
			return (short) (((int) data[index + 1]) << 8 | ((int) data[index]));
		}
	}

	public int getDWord(int index)
	{
		if (byteOrder == ByteOrder.Motorola)
		{

			return ((int) data[index]) << 24 | ((int) data[index + 1]) << 16
					| ((int) data[index + 2]) << 8 | ((int) data[index + 3]);
		} else
		{
			return ((int) data[index]) | ((int) data[index + 1]) << 8
					| ((int) data[index + 2]) << 16
					| ((int) data[index + 3]) << 24;
		}
	}

	public int getSDWord(int index)
	{
		if (byteOrder == ByteOrder.Motorola)
		{

			return (int) (((int) data[index]) << 24
					| ((int) data[index + 1]) << 16
					| ((int) data[index + 2]) << 8 | ((int) data[index + 3]));
		} else
		{
			return (int) (((int) data[index]) | ((int) data[index + 1]) << 8
					| ((int) data[index + 2]) << 16 | ((int) data[index + 3]) << 24);
		}
	}

	public long getULong(int index)
	{
		if (byteOrder == ByteOrder.Motorola)
		{

			return ((int) data[index]) << 24 | ((int) data[index + 1]) << 16
					| ((int) data[index + 2]) << 8 | ((int) data[index + 3]);
		} else
		{
			return ((int) data[index]) | ((int) data[index + 1]) << 8
					| ((int) data[index + 2]) << 16
					| ((int) data[index + 3]) << 24;
		}
	}

	public long getSLong(int index)
	{
		if (byteOrder == ByteOrder.Motorola)
		{
			return (int) (((int) data[index]) << 24
					| ((int) data[index + 1]) << 16
					| ((int) data[index + 2]) << 8 | ((int) data[index + 3]));
		} else
		{
			return (int) (((int) data[index]) | ((int) data[index + 1]) << 8
					| ((int) data[index + 2]) << 16 | ((int) data[index + 3]) << 24);
		}
	}

	public long[] getULongArray(int index, int length)
	{
		long[] arr = new long[length];
		for (int i = 0; i < length; i++)
			arr[i] = data[index + i];
		return arr;
	}

	public byte[] getArray(int index, int length)
	{
		byte[] arr = new byte[length];
		for (int i = 0; i < length; i++)
		{
			arr[i] = data[index + i];
		}
		return arr;
	}

	public void putByte(int index, int b)
	{
		data[index] = (byte) b;
	}

	public void putSByte(int index, double b)
	{
		data[index] = (byte) b;
	}

	public void putWord(int index, int s)
	{
		if (byteOrder == ByteOrder.Motorola)
		{
			data[index] = (byte) (s >> 8 & 0xFF);
			data[index + 1] = (byte) ((s) & 0xFF);
		} else
		{
			data[index] = (byte) ((s) & 0xFF);
			data[index + 1] = (byte) (s >> 8 & 0xFF);
		}
	}

	public void putSWord(int index, int s)
	{
		if (byteOrder == ByteOrder.Motorola)
		{
			data[index] = (byte) (s >> 8 & 0xFF);
			data[index + 1] = (byte) ((s) & 0xFF);
		} else
		{
			data[index] = (byte) ((s) & 0xFF);
			data[index + 1] = (byte) (s >> 8 & 0xFF);
		}
	}

	public void putDWord(int index, int s)
	{
		if (byteOrder == ByteOrder.Motorola)
		{
			data[index] = (byte) ((s >> 24) & 0xFF);
			data[index + 1] = (byte) ((s >> 16) & 0xFF);
			data[index + 2] = (byte) ((s >> 8) & 0xFF);
			data[index + 3] = (byte) ((s) & 0xFF);
		} else
		{
			data[index] = (byte) ((s) & 0xFF);
			data[index + 1] = (byte) ((s >> 8) & 0xFF);
			data[index + 2] = (byte) ((s >> 16) & 0xFF);
			data[index + 3] = (byte) ((s >> 24) & 0xFF);
		}
	}

	public void putULong(int index, long s)
	{
		if (byteOrder == ByteOrder.Motorola)
		{
			data[index] = (byte) ((s >> 24) & 0xFF);
			data[index + 1] = (byte) ((s >> 16) & 0xFF);
			data[index + 2] = (byte) ((s >> 8) & 0xFF);
			data[index + 3] = (byte) ((s) & 0xFF);
		} else
		{
			data[index] = (byte) ((s) & 0xFF);
			data[index + 1] = (byte) ((s >> 8) & 0xFF);
			data[index + 2] = (byte) ((s >> 16) & 0xFF);
			data[index + 3] = (byte) ((s >> 24) & 0xFF);
		}
	}

	public void putSLong(int index, long s)
	{
		if (byteOrder == ByteOrder.Motorola)
		{
			data[index] = (byte) ((s >> 24) & 0xFF);
			data[index + 1] = (byte) ((s >> 16) & 0xFF);
			data[index + 2] = (byte) ((s >> 8) & 0xFF);
			data[index + 3] = (byte) ((s) & 0xFF);
		} else
		{
			data[index] = (byte) ((s) & 0xFF);
			data[index + 1] = (byte) ((s >> 8) & 0xFF);
			data[index + 2] = (byte) ((s >> 16) & 0xFF);
			data[index + 3] = (byte) ((s >> 24) & 0xFF);
		}
	}

	public void putArray(int index, byte[] array, int length)
	{
		for (int i = 0; i < length; i++)
		{
			data[index + i] = array[i];
		}
	}

	public void putUlongArray(int index, long[] array, int length)
	{
		for (int i = 0; i < length; i++)
		{
			data[index + i] = (byte) array[i];
		}
	}

	public byte[] reverseBytes(byte[] arr)
	{
		byte[] result = new byte[arr.length];
		for (int i = 0; i < result.length; i++)
		{
			result[i] = arr[result.length - i - 1];
		}
		return result;
	}
	
	public void getFramePading(int index){
		for(int i = index; i<13 ; i++){
			data[index] = 0;
		}
		return ;
	}

}
