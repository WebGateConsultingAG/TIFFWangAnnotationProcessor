package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;

import biz.webgate.tools.tiffwangannotation.ParseTools;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiGroupAnnotation extends AbstractAnnotation {

	private String name;
	private int blockType;
	private int blockSize;
	private int innerSize;

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		name = ParseTools.readChar(buffer, size);
		this.innerSize = size;
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

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getInnerSize() {
		return innerSize;
	}

	public void setInnerSize(int innerSize) {
		this.innerSize = innerSize;
	}

}
