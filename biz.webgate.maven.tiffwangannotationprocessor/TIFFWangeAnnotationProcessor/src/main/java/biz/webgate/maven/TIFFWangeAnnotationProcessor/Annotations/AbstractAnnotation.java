package biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations;

import java.nio.ByteBuffer;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.IAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotationParser;

public abstract class AbstractAnnotation implements IAnnotation {

	/* (non-Javadoc)
	 * @see biz.webgate.maven.TIFFWangeAnnotationProcessor.IAnnotation#deserialize(biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotationParser, java.nio.ByteBuffer, int)
	 */
	public abstract void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size);

	/* (non-Javadoc)
	 * @see biz.webgate.maven.TIFFWangeAnnotationProcessor.IAnnotation#serialize()
	 */
	public abstract Byte[] serialize();
	
	/* (non-Javadoc)
	 * @see biz.webgate.maven.TIFFWangeAnnotationProcessor.IAnnotation#getAnnotationName()
	 */
	public abstract String getAnnotationName();

}
