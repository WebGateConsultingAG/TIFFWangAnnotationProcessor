package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import java.nio.ByteBuffer;

public abstract class WangAnnotation {

	public abstract void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size);

	public abstract Byte[] serialize();
	
	public abstract String getAnnotationName();

}
