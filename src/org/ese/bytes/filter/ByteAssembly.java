package org.ese.bytes.filter;

public class ByteAssembly {
	
	private String b;
	
	private int[] array;
	
	private int sequence;

	public ByteAssembly(String b, int[] array, int sequence) {
		super();
		this.b = b;
		this.array = array;
		this.sequence = sequence;
	}

	public String getByte() {
		return b;
	}

	public void setByte(String b) {
		this.b = b;
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
}
