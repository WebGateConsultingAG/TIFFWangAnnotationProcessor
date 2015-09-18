package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiInitAnnotation extends AbstractAnnotation {

	private byte[] value;
	private int blockType;
	private int blockSize;
	private int innerSize;
	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		this.innerSize = size;
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
		return "OiInitls";
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
