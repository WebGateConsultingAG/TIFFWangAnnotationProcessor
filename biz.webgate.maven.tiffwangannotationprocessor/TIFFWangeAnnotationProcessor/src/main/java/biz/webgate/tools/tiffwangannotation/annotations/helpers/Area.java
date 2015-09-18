package biz.webgate.tools.tiffwangannotation.annotations.helpers;

import java.nio.ByteBuffer;

import biz.webgate.tools.tiffwangannotation.ParseTools;

public class Area {

	private final int topX;
	private final int topY;
	private final int bottomX;
	private final int bottomY;

	public Area(int topX, int topY, int bottomX, int bottomY) {
		super();
		this.topX = topX;
		this.topY = topY;
		this.bottomX = bottomX;
		this.bottomY = bottomY;
	}

	public static Area buildArea(ByteBuffer buffer) {
		int topX = buffer.getInt();
		int topY = buffer.getInt();
		int bottomX = buffer.getInt();
		int bottomY = buffer.getInt();
		return new Area(topX, topY, bottomX, bottomY);
	}

	public int getTopX() {
		return topX;
	}

	public int getTopY() {
		return topY;
	}

	public int getBottomX() {
		return bottomX;
	}

	public int getBottomY() {
		return bottomY;
	}
	
	public byte[] getAsByteArray(){
		Byte[] blist = new Byte[16];
		int i = 0;
		i=ParseTools.fillBlistIncreaseI(ByteBuffer.allocate(4).putInt(topX).array(), blist, i);
		i=ParseTools.fillBlistIncreaseI(ByteBuffer.allocate(4).putInt(topY).array(), blist, i);
		i=ParseTools.fillBlistIncreaseI(ByteBuffer.allocate(4).putInt(bottomX).array(), blist, i);
		i=ParseTools.fillBlistIncreaseI(ByteBuffer.allocate(4).putInt(bottomY).array(), blist, i);
		return ParseTools.createFromByteObect(blist);
	}
	
}
