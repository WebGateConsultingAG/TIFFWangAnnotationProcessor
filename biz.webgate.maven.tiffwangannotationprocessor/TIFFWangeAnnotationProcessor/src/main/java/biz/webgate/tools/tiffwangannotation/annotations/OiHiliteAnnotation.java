package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

//TODO: What purpose has value?
public class OiHiliteAnnotation extends AbstractAnnotation {

	private byte[] value;
	private int blockType;
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

	public int getBlockType() {
		return blockType;
	}

	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}

	public int getInnerSize() {
		return value.length;
	}
}
