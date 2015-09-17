package biz.webgate.tools.tiffwangannotation.annotations.helpers;

import java.nio.ByteBuffer;

import biz.webgate.tools.tiffwangannotation.ParseTools;
/*
 * 		https://msdn.microsoft.com/en-us/library/windows/desktop/dd162938%28v=vs.85%29.aspx
 */
public class RGBColor {

	private int red;
	private int green;
	private int blue;
	private int reserve;
	
	public static RGBColor buildColor(ByteBuffer buffer) {
		int b = (buffer.get() & 0xff);
		int g = (buffer.get() & 0xff);
		int r = (buffer.get() & 0xff);
		int reserve = (buffer.get() & 0xff);
		return new RGBColor(r, g, b, reserve);
	}
	
	public RGBColor(int r, int g, int b, int reserve) {
		super();
		this.red = r;
		this.green = g;
		this.blue = b;
		this.reserve = reserve;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public byte[] getAsByteArray(){
		byte[] reserveb = ByteBuffer.allocate(4).putInt(reserve).array();
		byte[] redb = ByteBuffer.allocate(4).putInt(this.red).array();
		byte[] greenb = ByteBuffer.allocate(4).putInt(this.green).array();
		byte[] blueb = ByteBuffer.allocate(4).putInt(this.blue).array();
		int maxb = reserveb.length + redb.length + greenb.length + blueb.length;
		Byte[] blist = new Byte[maxb];
		int i = maxb-1;
		i = ParseTools.INSTANCE.fillBlist(reserveb, blist, i);
		i = ParseTools.INSTANCE.fillBlist(redb, blist, i);
		i = ParseTools.INSTANCE.fillBlist(greenb, blist, i);
		i = ParseTools.INSTANCE.fillBlist(blueb, blist, i);
		byte[] retlist = ParseTools.INSTANCE.createFromByteObect(blist);
		return retlist;
	}
	
}
