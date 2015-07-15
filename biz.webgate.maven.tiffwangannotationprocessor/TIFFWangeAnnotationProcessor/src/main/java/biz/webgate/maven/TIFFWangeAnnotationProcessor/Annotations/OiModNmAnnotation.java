package biz.webgate.maven.TIFFWangeAnnotationProcessor.Annotations;

import java.nio.ByteBuffer;
import java.util.Date;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotationParser;

public class OiModNmAnnotation extends WangAnnotation {

	private String name;
	private Date date;
	
	
	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		name = parser.readChar(buffer, size-4);
		int time = buffer.getInt();
		date = new Date(time);
	}

	@Override
	public Byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnnotationName() {
		return "OiOwnNm";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return name +" "+ date;
	}
}
