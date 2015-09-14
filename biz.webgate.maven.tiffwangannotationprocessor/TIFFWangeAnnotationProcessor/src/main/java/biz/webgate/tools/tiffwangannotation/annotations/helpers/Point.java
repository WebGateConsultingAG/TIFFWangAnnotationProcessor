package biz.webgate.tools.tiffwangannotation.annotations.helpers;

import java.nio.ByteBuffer;

public class Point {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public static Point buildPoint(ByteBuffer buffer) {
		int x = buffer.getInt();
		int y = buffer.getInt();
		return new Point(x,y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	
}
