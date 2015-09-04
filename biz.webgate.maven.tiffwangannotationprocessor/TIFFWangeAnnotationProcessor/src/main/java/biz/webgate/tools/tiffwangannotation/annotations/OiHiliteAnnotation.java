package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;

import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiHiliteAnnotation extends AbstractAnnotation {

	private byte[] value;

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		value = new byte[size];
		buffer.get(value);
	}

	@Override
	public Byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnnotationName() {
		return "OiHilite";
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return new String(value);
	}
}
