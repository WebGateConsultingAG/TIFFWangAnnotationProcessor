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
		text = ParseTools.INSTANCE.readChar(buffer, anoTextLenght);
		buffer.position(start + size);
	}

	@Override
	public Byte[] serialize() {
		int maxb = 0;
		byte[] textBytes = text.getBytes(StandardCharsets.ISO_8859_1);
		
		byte[] anoTextLenghtBytes = ByteBuffer.allocate(4).putInt(anoTextLenght).array();
		byte[] creationScaleBytes = ByteBuffer.allocate(4).putInt(creationScale).array();
		byte[] reserved1Bytes = ByteBuffer.allocate(4).putInt(reserved1).array();
		byte[] currentOrientationBytes = ByteBuffer.allocate(4).putInt(currentOrientation).array();
		
		
		
		maxb = textBytes.length + anoTextLenghtBytes.length + creationScaleBytes.length + reserved1Bytes.length + currentOrientationBytes.length;
		Byte[] blist = new Byte[maxb];
		int i = maxb-1;
		i = ParseTools.INSTANCE.fillBlistwidthString(textBytes, blist, i);
		i = ParseTools.INSTANCE.fillBlist(anoTextLenghtBytes,blist,i);
		i = ParseTools.INSTANCE.fillBlist(creationScaleBytes,blist,i);
		i = ParseTools.INSTANCE.fillBlist(reserved1Bytes,blist,i);
		i = ParseTools.INSTANCE.fillBlist(currentOrientationBytes,blist,i);
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
