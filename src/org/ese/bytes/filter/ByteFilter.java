package org.ese.bytes.filter;

import java.util.ArrayList;
import java.util.List;

public class ByteFilter {

	private String b;
	
	private int[] array;
	
	public ByteFilter(ByteAssembly byteAssem) {
		this.b = byteAssem.getByte();
		this.array = byteAssem.getArray();
	}
	
	public String[] getByteSegMent() {
		List<String> list = new ArrayList<String>();
		int m = 0;
		for(int i : array) {
			String newByte = b.substring(m, m + i);
			m += i;
			list.add(newByte);
		}
		return list.toArray(new String[list.size()]);
	}
	
}
