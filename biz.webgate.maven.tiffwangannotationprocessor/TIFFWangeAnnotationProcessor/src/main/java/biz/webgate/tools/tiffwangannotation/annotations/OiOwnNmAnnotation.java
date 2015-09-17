package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import biz.webgate.tools.tiffwangannotation.ParseTools;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiOwnNmAnnotation extends AbstractAnnotation {

	private String name;
	private Date date;
	
	
	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		name = ParseTools.INSTANCE.readChar(buffer, size-4);
		long time = buffer.getInt();
		time = time * 1000;
		date = new Date(time);
	}

	@Override
	public Byte[] serialize() {
		long time = date.getTime();
		int maxb = 0;
		int intime = (int)(time/1000);
		byte[] dateBytes = ByteBuffer.allocate(4).putInt(intime).array();		
		byte[] textBytes = name.getBytes(StandardCharsets.ISO_8859_1);
		maxb = textBytes.length + dateBytes.length;
		Byte[] blist = new Byte[maxb];
		
		int i = maxb-1;
		i = ParseTools.INSTANCE.fillBlist(dateBytes, blist, i);
		i = ParseTools.INSTANCE.fillBlistwidthString(textBytes, blist, i);
		return blist;		
	}

	@Override
	public String getAnnotationName() {
		return "OiModNm";
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
