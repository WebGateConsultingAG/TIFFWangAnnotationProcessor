package biz.webgate.tools.tiffwangannotation;

import java.nio.ByteBuffer;
import java.util.List;

public interface IAnnotation {

	public abstract void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size);

	public abstract Byte[] serialize();

	public abstract String getAnnotationName();

	public abstract void addAnnotation(IAnnotation annotation);
	public abstract List<IAnnotation> getAnnotations();
	public abstract boolean hasAnnoations();

}