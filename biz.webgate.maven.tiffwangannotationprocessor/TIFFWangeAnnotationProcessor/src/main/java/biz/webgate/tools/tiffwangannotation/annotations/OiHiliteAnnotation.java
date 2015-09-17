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
		Byte[] blist = new Byte[value.length];
		int anz = 0;
		for(byte b : value){
			blist[anz] = b;
			anz++;
		}
		return blist;
		
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
