package biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations;

import java.nio.ByteBuffer;

import javax.swing.text.Position;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotationParser;

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
		text = parser.readChar(buffer, anoTextLenght);
		buffer.position(start + size);
	}

	@Override
	public Byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnnotationName() {
		return "OiGroup";
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
