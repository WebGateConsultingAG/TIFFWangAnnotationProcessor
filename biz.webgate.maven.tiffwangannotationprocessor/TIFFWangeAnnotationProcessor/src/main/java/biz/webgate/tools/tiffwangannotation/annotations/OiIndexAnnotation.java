package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import biz.webgate.tools.tiffwangannotation.ParseTools;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiIndexAnnotation extends AbstractAnnotation {
	private int blockType;
	private String name;
	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		name = ParseTools.readChar(buffer, size);
	}

	@Override
	public Byte[] serialize() {
		Byte[] textBytes = ParseTools.buildByteStructure(name);
		return textBytes;
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

	public int getBlockType() {
		return blockType;
	}

	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}


	public int getInnerSize() {
		return serialize().length;
	}

}
