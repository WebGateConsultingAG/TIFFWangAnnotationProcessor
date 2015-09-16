package biz.webgate.tools.tiffwangannotation.annotations;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;
import biz.webgate.tools.tiffwangannotation.annotations.helpers.Point;

public class OiAnoDatAnnotation extends AbstractAnnotation {

	private int maxPoints;
	private int pointCount;
	private List<Point> points = new ArrayList<Point>();

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		int start = buffer.position();
		maxPoints = buffer.getInt();
		pointCount = buffer.getInt();
		for (int i = 0; i < pointCount; i++) {
			Point point = Point.buildPoint(buffer);
			points.add(point);
		}
		buffer.position(start + size);
	}

	@Override
	public Byte[] serialize() {
		
		int maxb = 0;
		byte[] max = ByteBuffer.allocate(4).putInt(maxPoints).array();
		byte[] pc = ByteBuffer.allocate(4).putInt(pointCount).array();
		byte[] spx = ByteBuffer.allocate(4).putInt(points.get(0).getX()).array();
		byte[] spy = ByteBuffer.allocate(4).putInt(points.get(0).getY()).array();
		byte[] epx = ByteBuffer.allocate(4).putInt(points.get(1).getX()).array();
		byte[] epy = ByteBuffer.allocate(4).putInt(points.get(1).getY()).array();
		maxb = max.length+pc.length+spx.length+spy.length+epx.length+epy.length;
		Byte[] blist = new Byte[maxb];
		int i = 0;
		for(byte b : max){
			blist[i] = b;
			i++;
		}
		for(byte b : pc){
			blist[i] = b;
			i++;
		}
		for(byte b : spx){
			blist[i] = b;
			i++;
		}
		for(byte b : spy){
			blist[i] = b;
			i++;
		}
		for(byte b : epx){
			blist[i] = b;
			i++;
		}
		for(byte b : epy){
			blist[i] = b;
			i++;
		}
		return blist;
	}

	@Override
	public String getAnnotationName() {
		return "OiAnoDat";
	}

	@Override
	public String toString() {
		return "Points: "+points.size();
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public int getPointCount() {
		return pointCount;
	}

	public void setPointCount(int pointCount) {
		this.pointCount = pointCount;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
	
}
