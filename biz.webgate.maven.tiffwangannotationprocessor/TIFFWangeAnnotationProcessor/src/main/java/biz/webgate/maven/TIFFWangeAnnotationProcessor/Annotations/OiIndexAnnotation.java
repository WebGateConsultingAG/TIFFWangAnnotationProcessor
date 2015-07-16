package biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations;

import java.nio.ByteBuffer;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotationParser;

public class OiIndexAnnotation extends AbstractAnnotation {

	private String name;

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		name = parser.readChar(buffer, size);
	}

	@Override
	public Byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnnotationName() {
		return "OiIndex";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
