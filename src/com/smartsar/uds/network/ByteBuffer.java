package com.smartsar.uds.network;

public class ByteBuffer
{

	private byte[] data;

	private int position = 0;

	public byte[] getData()
	{
		return data;
	}

	public void setData(byte[] data)
	{
		this.data = data;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public ByteBuffer(int length)
	{
		data = new byte[length];
	}

	public void Put(byte[] bytes)
	{
		for (int i = 0; i < bytes.length && position < data.length; i++, position++)
		{
			data[position] = bytes[i];
		}
	}

}
