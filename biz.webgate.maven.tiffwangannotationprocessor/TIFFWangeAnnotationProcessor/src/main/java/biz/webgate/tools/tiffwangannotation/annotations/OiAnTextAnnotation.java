package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import biz.webgate.tools.tiffwangannotation.ParseTools;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiAnTextAnnotation extends AbstractAnnotation {

	private int currentOrientation;
	private int reserved1;
	private int creationScale;
	private int anoTextLenght;
	private String text;
	private int blockType;
	private int blockSize;
	private int innerSize;
	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		this.innerSize = size;
		int start = buffer.position();
		currentOrientation = buffer.getInt();
		reserved1 = buffer.getInt();
		creationScale = buffer.getInt();
		anoTextLenght = buffer.getInt();
		text = ParseTools.readChar(buffer, anoTextLenght);
		buffer.position(start + size);
	}

	@Override
	public Byte[] serialize() {
		byte[] textBytes = text.getBytes(StandardCharsets.ISO_8859_1);
		int i = 0;
		byte[] realText = new byte[this.anoTextLenght+4];
		for(int j = 0 ;j < this.anoTextLenght+4;j++){
			if(textBytes.length>j){
				realText[j] = textBytes[j];
			}else{
				realText[j] = 0;
			}
		}	
		int max = 20+textBytes.length;
		Byte[] blist = new Byte[max];
		i=ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(currentOrientation).array(),blist,i);
		i=ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(reserved1).array(),blist,i);
		i=ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(creationScale).array(),blist,i);
		i=ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(anoTextLenght).array(),blist,i);
		i=ParseTools.fillBlistIncreaseI(realText,blist,i);

		return blist;
	}
	
	@Override
	public String getAnnotationName() {
		return "OiAnText";
	}

	public String getText() {
		return text;
	}

	public void setText(String name) {
		this.text = name;
	}

	@Override
	public String toString() {
		return text;
	}

	public int getCurrentOrientation() {
		return currentOrientation;
	}

	public void setCurrentOrientation(int currentOrientation) {
		this.currentOrientation = currentOrientation;
	}

	public int getReserved1() {
		return reserved1;
	}

	public void setReserved1(int reserved1) {
		this.reserved1 = reserved1;
	}

	public int getCreationScale() {
		return creationScale;
	}

	public void setCreationScale(int creationScale) {
		this.creationScale = creationScale;
	}

	public int getAnoTextLenght() {
		return anoTextLenght;
	}

	public void setAnoTextLenght(int anoTextLenght) {
		this.anoTextLenght = anoTextLenght;
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
