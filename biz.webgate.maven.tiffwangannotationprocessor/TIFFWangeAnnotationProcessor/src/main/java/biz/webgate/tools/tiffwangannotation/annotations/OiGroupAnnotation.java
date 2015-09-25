package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import biz.webgate.tools.tiffwangannotation.ParseTools;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiGroupAnnotation extends AbstractAnnotation {

	private String name;
	private int blockType;

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		name = ParseTools.readChar(buffer, size);
	}

	@Override
	public Byte[] serialize() {
		try {
			byte[] textBytes = name.getBytes("ISO_8859_1");
			int maxb = textBytes.length;
			Byte[] blist = new Byte[maxb];
			int i = 0;
			i = ParseTools.fillBlistIncreaseI(textBytes, blist, i);
			return blist;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public String getAnnotationName() {
		return "OiGroup";
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

	public int getBlockType() {
		return blockType;
	}

	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}

	public int getInnerSize() {
		byte[] textBytes = name.getBytes(Charset.forName("ISO_8859_1"));
		int maxb = textBytes.length;
		return maxb;
	}

}
