package biz.webgate.tools.tiffwangannotation;

import java.nio.ByteBuffer;

public class ParseTools {

	public static int addByte(byte b,Byte[] blist,int i){
		blist[i] = b;
		i++;
		return i;
	}
	
	
	public static int fillBlistIncreaseI(byte[] bytes,Byte[] blist,int i){
		for(byte b : bytes){
			blist[i]=b;
			i++;
		}
		return i;
	}
	
	
	public static int reverseBListIncrease(byte[] bytes,Byte[] blist,int i){
		for(int j=bytes.length-1;j>=0;j--){
			blist[i]=bytes[j];
			i++;
		}
		return i;
	}

	
	public static String readChar(ByteBuffer buffer, int size) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int value = buffer.get() & 0xff;
			sb.append(Character.toChars(value));
		}
		
		return sb.toString();//.trim() commented out becoause there comes wrong byte Stream back
	}
	
	public static Byte[] buildByteStructure(String value) {
		Byte[] rc = new Byte[value.length()];
		for (int pos = 0; pos < value.length() ;pos++) {
			char c= value.charAt(pos);
			byte b = (byte)(c & 0xff);
			rc[pos] = b;
		}
		return rc;
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
