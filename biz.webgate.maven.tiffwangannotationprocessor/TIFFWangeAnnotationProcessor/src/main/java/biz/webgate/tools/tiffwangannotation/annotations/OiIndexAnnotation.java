package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import biz.webgate.tools.tiffwangannotation.ParseTools;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiIndexAnnotation extends AbstractAnnotation {

	private String name;

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		name = ParseTools.readChar(buffer, size);
	}

	@Override
	public Byte[] serialize() {
		byte[] textBytes = name.getBytes(StandardCharsets.ISO_8859_1);
		int maxb = textBytes.length;
		Byte[] blist = new Byte[maxb];
		int i = 0;
		i=ParseTools.fillBlistIncreaseI(textBytes,blist,i);
		return blist;
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
