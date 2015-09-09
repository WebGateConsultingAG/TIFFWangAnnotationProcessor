package biz.webgate.tools.tiffwangannotation.annotations.helpers;

import java.nio.ByteBuffer;

import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;

/*
 https://msdn.microsoft.com/en-us/library/windows/desktop/dd145037%28v=vs.85%29.aspx
 http://www.jasinskionline.com/windowsapi/ref/l/logfont.html
 */
public class LogFont {

	private long height;
	private long width;
	private long escapement;
	private long orientation;
	private long weight;
	private byte italic;
	private byte underline;
	private byte strikeout;
	private byte charset;
	private byte outPrecision;
	private byte clipPrecision;
	private byte quality;
	private byte pitchAndFamily;
	private String faceName;

	public static LogFont buildLogFont(WangAnnotationParser parser, ByteBuffer buffer) {
		LogFont font = new LogFont();
		font.height = buffer.getLong();
		font.width = buffer.getLong();
		font.escapement = buffer.getLong();
		font.faceName = parser.readChar(buffer, 32);
		font.orientation = buffer.getLong();

		font.weight = buffer.getLong();
		font.italic = buffer.get();
		font.underline = buffer.get();
		font.strikeout = buffer.get();
		font.charset = buffer.get();
		font.outPrecision = buffer.get();
		font.clipPrecision = buffer.get();
		font.quality = buffer.get();
		font.pitchAndFamily = buffer.get();
		buffer.getInt();
		buffer.getInt();
		buffer.getInt();
		return font;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getEscapement() {
		return escapement;
	}

	public void setEscapement(long escapement) {
		this.escapement = escapement;
	}

	public long getOrientation() {
		return orientation;
	}

	public void setOrientation(long orientation) {
		this.orientation = orientation;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public byte getItalic() {
		return italic;
	}

	public void setItalic(byte italic) {
		this.italic = italic;
	}

	public byte getUnderline() {
		return underline;
	}

	public void setUnderline(byte underline) {
		this.underline = underline;
	}

	public byte getStrikeout() {
		return strikeout;
	}

	public void setStrikeout(byte strikeout) {
		this.strikeout = strikeout;
	}

	public byte getCharset() {
		return charset;
	}

	public void setCharset(byte charset) {
		this.charset = charset;
	}

	public byte getOutPrecision() {
		return outPrecision;
	}

	public void setOutPrecision(byte outPrecision) {
		this.outPrecision = outPrecision;
	}

	public byte getClipPrecision() {
		return clipPrecision;
	}

	public void setClipPrecision(byte clipPrecision) {
		this.clipPrecision = clipPrecision;
	}

	public byte getQuality() {
		return quality;
	}

	public void setQuality(byte quality) {
		this.quality = quality;
	}

	public byte getPitchAndFamily() {
		return pitchAndFamily;
	}

	public void setPitchAndFamily(byte pitchAndFamily) {
		this.pitchAndFamily = pitchAndFamily;
	}

	public String getFaceName() {
		return faceName;
	}

	public void setFaceName(String faceName) {
		this.faceName = faceName;
	}

}
