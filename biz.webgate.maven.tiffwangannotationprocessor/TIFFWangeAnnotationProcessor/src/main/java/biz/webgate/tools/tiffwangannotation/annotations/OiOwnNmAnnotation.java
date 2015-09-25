package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;

import biz.webgate.tools.tiffwangannotation.ParseTools;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

public class OiOwnNmAnnotation extends AbstractAnnotation {

	private String name;
	private Date date;
	private int blockType;

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		name = ParseTools.readChar(buffer, size - 4);
		long time = buffer.getInt();
		time = time * 1000;
		date = new Date(time);
	}

	@Override
	public Byte[] serialize() {
		long time = date.getTime();
		int maxb = 0;
		int intime = (int) (time / 1000);

		byte[] dateBytes = ByteBuffer.allocate(4).putInt(intime).array();
		byte[] textBytes = name.getBytes(Charset.forName("ISO_8859_1"));
		maxb = textBytes.length + dateBytes.length;
		Byte[] blist = new Byte[maxb];
		int i = 0;
		i = ParseTools.fillBlistIncreaseI(textBytes, blist, i);
		i = ParseTools.reverseBListIncrease(dateBytes, blist, i);
		return blist;
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
		return name + " " + date;
	}

	public int getBlockType() {
		return blockType;
	}

	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}

	public int getInnerSize() {
		byte[] textBytes = name.getBytes(Charset.forName("ISO_8859_1"));
		return textBytes.length + 4;
	}

}
