package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public enum WangAnnotationParser {
	INSTANCE;

	public WangAnnotationContainer parse(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		int header = buffer.getInt();
		int win32 = buffer.getInt();
		System.out.println("header:" + header);
		System.out.println("win32r:" + win32);
		System.out.println("type:" + buffer.getInt());
		System.out.println("size:" + buffer.getInt());
		System.out.println("name:" + get8ByteName(buffer));
		// System.out.println("name:"+buffer.getInt() +" "+buffer.getInt());
		int innerSize = buffer.getInt();
		System.out.println("size2:" + innerSize);
		System.out.println("value:" + readChar(buffer, innerSize-4));
		int dateInt = buffer.getInt();
		System.out.println(dateInt);

		System.out.println("type:" + buffer.getInt());
		System.out.println("size:" + buffer.getInt());
		System.out.println("name:" + get8ByteName(buffer));
		// System.out.println("name:"+buffer.getInt() +" "+buffer.getInt());
		innerSize = buffer.getInt();
		System.out.println("size2:" + innerSize);
		System.out.println("value:" + readChar(buffer, innerSize-4));
		dateInt = buffer.getInt();
		System.out.println(dateInt);

		
		return null;
	}

	private String get8ByteName(ByteBuffer buffer) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int value = buffer.get() & 0xff;
			sb.append(Character.toChars(value));
		}
		return sb.toString();
	}
	
	private String readChar(ByteBuffer buffer, int size) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int value = buffer.get() & 0xff;
			sb.append(Character.toChars(value));
		}
		return sb.toString();
		
	}
}
