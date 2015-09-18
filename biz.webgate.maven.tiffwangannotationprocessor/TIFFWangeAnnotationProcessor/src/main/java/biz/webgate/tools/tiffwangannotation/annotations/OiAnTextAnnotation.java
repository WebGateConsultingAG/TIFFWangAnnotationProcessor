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

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
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
		int max = 16+textBytes.length;
		Byte[] blist = new Byte[max];
		i=ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(currentOrientation).array(),blist,i);
		i=ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(reserved1).array(),blist,i);
		i=ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(creationScale).array(),blist,i);
		i=ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(anoTextLenght).array(),blist,i);
		i=ParseTools.fillBlistIncreaseI(textBytes,blist,i);
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
	
}
