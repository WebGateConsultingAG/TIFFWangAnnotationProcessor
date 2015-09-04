package biz.webgate.tools.tiffwangannotation.annotations.helpers;

import java.nio.ByteBuffer;
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
	
	
}
