package biz.webgate.tools.tiffwangannotation;

import java.nio.ByteBuffer;

public class ParseTools {
	
	public static int fillBlist(byte[] bytes,Byte[] blist,int i){
		for(byte b : bytes){
			blist[i] = b;
			i--;
		}
		return i;
	}
	
	public static int fillBlistBeginAtEnd(byte[] bytes,Byte[] blist,int i){
		for(int c = bytes.length-1; c>=0;c--){
			blist[i]=bytes[c];
			i--;
		}
		return i;
	}
	
	public static String readChar(ByteBuffer buffer, int size) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int value = buffer.get() & 0xff;
			sb.append(Character.toChars(value));
		}
		return sb.toString().trim();
	}
	
	public static byte[] createFromByteObect(Byte[] current){
		
		byte[] blist = new byte[current.length];
		for(int i = 0;i<current.length;i++){
			blist[i] = current[i];
		}
		return blist;
	}
	
	public static Byte[] createFromByteSimple(byte[] current){
		Byte[] blist = new Byte[current.length];
		for(int i = 0;i<current.length;i++){
			blist[i] = current[i];
		}
		return blist;
	}
}
