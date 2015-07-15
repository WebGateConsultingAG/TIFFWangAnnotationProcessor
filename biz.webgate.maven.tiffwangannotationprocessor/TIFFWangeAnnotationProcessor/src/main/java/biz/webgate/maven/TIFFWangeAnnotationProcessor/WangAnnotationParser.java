package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import java.nio.ByteBuffer;

public enum WangAnnotationParser {
	INSTANCE;

	public WangAnnotationContainer parse(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		int header = buffer.getInt();
		int win32 = buffer.getInt();
		System.out.println("header:" + header);
		System.out.println("win32r:" + win32);

		return null;
	}

}
