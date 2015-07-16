package biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations;

import java.nio.ByteBuffer;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotationParser;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.helpers.Area;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.helpers.LogFont;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.helpers.RGBColor;

public class Type5Annotation extends AbstractAnnotation {

	private int type;
	private Area area;
	private RGBColor color1;
	private RGBColor color2;
	private LogFont font;
	private int highligt;
	private int transparent;
	private int lineSize;
	private int reserved1;
	private int reserved2;
	private int reserved3;
	private int time;
	private int visible;
	private int reserved4;
	private long reserved10;

	@Override
	public void deserialize(WangAnnotationParser parser, ByteBuffer buffer, int size) {
		type = buffer.getInt();
		area = Area.buildArea(buffer);
		color1 = RGBColor.buildColor(buffer);
		color2 = RGBColor.buildColor(buffer);
		highligt = buffer.getInt();
		transparent = buffer.getInt();
		lineSize = buffer.getInt();
		reserved1 = buffer.getInt();
		reserved2 = buffer.getInt();
		font = LogFont.buildLogFont(parser,buffer);
		reserved3 = buffer.getInt();
		time = buffer.getInt();
		visible = buffer.getInt();
		reserved4 = buffer.getInt();
		reserved10 = buffer.getLong();

	}

	@Override
	public Byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnnotationName() {
		return "Type5Annotation:" + type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public RGBColor getColor1() {
		return color1;
	}

	public void setColor1(RGBColor color1) {
		this.color1 = color1;
	}

	public RGBColor getColor2() {
		return color2;
	}

	public void setColor2(RGBColor color2) {
		this.color2 = color2;
	}

	public LogFont getFont() {
		return font;
	}

	public void setFont(LogFont font) {
		this.font = font;
	}

	public int getHighligt() {
		return highligt;
	}

	public void setHighligt(int highligt) {
		this.highligt = highligt;
	}

	public int getTransparent() {
		return transparent;
	}

	public void setTransparent(int transparent) {
		this.transparent = transparent;
	}

	public int getLineSize() {
		return lineSize;
	}

	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}

	public int getReserved1() {
		return reserved1;
	}

	public void setReserved1(int reserved1) {
		this.reserved1 = reserved1;
	}

	public int getReserved2() {
		return reserved2;
	}

	public void setReserved2(int reserved2) {
		this.reserved2 = reserved2;
	}

	public int getReserved3() {
		return reserved3;
	}

	public void setReserved3(int reserved3) {
		this.reserved3 = reserved3;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public int getReserved4() {
		return reserved4;
	}

	public void setReserved4(int reserved4) {
		this.reserved4 = reserved4;
	}

	public long getReserved10() {
		return reserved10;
	}

	public void setReserved10(long reserved10) {
		this.reserved10 = reserved10;
	}

}
