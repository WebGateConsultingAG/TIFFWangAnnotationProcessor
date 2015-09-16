package biz.webgate.tools.tiffwangannotation;

import java.nio.ByteBuffer;

public enum ParseTools {
	INSTANCE;
	public int fillBlist(byte[] bytes,Byte[] blist,int i){
		for(byte b : bytes){
			blist[i] = b;
			i--;
		}
		return i;
	}
	
	public int fillBlistwidthString(byte[] bytes,Byte[] blist,int i){
		for(int c = bytes.length-1; c>=0;c--){
			blist[i]=bytes[c];
			i--;
		}
		return i;
	}
	
	public String readChar(ByteBuffer buffer, int size) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int value = buffer.get() & 0xff;
			sb.append(Character.toChars(value));
		}
		return sb.toString().trim();

	}
	
}
