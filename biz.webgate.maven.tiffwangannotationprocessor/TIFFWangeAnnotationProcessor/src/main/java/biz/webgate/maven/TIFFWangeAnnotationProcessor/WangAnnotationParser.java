package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.Type5Annotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.helpers.LogFont;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.helpers.RGBColor;

public enum WangAnnotationParser {
	INSTANCE;

	public WangAnnotationContainer parse(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		int header = buffer.getInt();
		int win32 = buffer.getInt();
		WangAnnotationContainer container = WangAnnotationContainer.buildContainer(header, win32);
		while (buffer.hasRemaining()) {
			int blockType = buffer.getInt();
			int blockSize = buffer.getInt();
			IAnnotation annotation = buildAnnoation(buffer, blockType, blockSize);
			if (annotation == null) {
				System.out.println("Stopped processing");
				break;
			}
			container.addAnnoation(annotation);
		}

		return container;
	}

	private IAnnotation buildAnnoation(ByteBuffer buffer, int blockType, int blockSize) {
		switch (blockType) {
		case 2:
			return processStandardType(buffer, blockSize);
		case 5:
			return processType5Annotation(buffer, blockSize);
		default:
			System.out.println("no strategie for: " + blockType);
		}
		return null;
	}


	private IAnnotation processStandardType(ByteBuffer buffer, int blockSize) {

		String name = get8ByteName(buffer);
		int innerSize = buffer.getInt();
		IAnnotation annotation = AnnotationFactory.getAnnotationByName(name);
		annotation.deserialize(this, buffer, innerSize);
		return annotation;
	}
	private IAnnotation processType5Annotation(ByteBuffer buffer, int blockSize) {
		int nPosition = buffer.position();
		Type5Annotation annotation = new Type5Annotation();
		annotation.deserialize(this, buffer, blockSize);
		System.out.println("Type 5 size: "+blockSize);
		System.out.println("utype:"+ annotation.getType());
		System.out.println("l1 (x-top)   :"+ annotation.getArea().getTopX());
		System.out.println("l2 (y-top)   :"+ annotation.getArea().getTopY());
		System.out.println("l3 (x-bottom):"+ annotation.getArea().getBottomX());
		System.out.println("l4 (y-bottom):"+ annotation.getArea().getBottomY());
		//20 Byte gelesen
		
		//https://msdn.microsoft.com/en-us/library/windows/desktop/dd162938%28v=vs.85%29.aspx
		System.out.println("C1 (B)   :"+ annotation.getColor1().getBlue());
		System.out.println("C1 (G)   :"+ annotation.getColor1().getGreen());
		System.out.println("C1 (R)   :"+ annotation.getColor1().getRed());
		System.out.println("C1 (Res) :"+ annotation.getColor1().getReserve());

		System.out.println("C2 (B)   :"+ annotation.getColor2().getBlue());
		System.out.println("C2 (G)   :"+ annotation.getColor2().getGreen());
		System.out.println("C2 (R)   :"+ annotation.getColor2().getRed());
		System.out.println("C2 (Res) :"+ annotation.getColor2().getReserve());

		//sollte boolean sein
		System.out.println("Highlight: "+annotation.getHighligt());
		System.out.println("Transparent: "+annotation.getTransparent());
		//8 Byte gelesen
		
		//UINT uLineSize;                             // The width of the line in pixels.
		System.out.println("ulineSize: "+annotation.getLineSize());
		//UINT uReserved1;                        // Reserved; must be set to 0.
		System.out.println("ureserved1: "+annotation.getReserved1());
		//UINT uReserved2;                        // Reserved; must be set to 0.
		System.out.println("ureserved2: "+annotation.getReserved2());
		//12 BYte gelesen
		
		//https://msdn.microsoft.com/en-us/library/windows/desktop/dd145037%28v=vs.85%29.aspx
		//LOGFONT lfFont;                    // The font information for the text, consisting of standard
		                                                                // font attributes of font size, name, style, effects, and
		//http://www.jasinskionline.com/windowsapi/ref/l/logfont.html                                                                // background color.
		//Type LOGFONT
		//lfHeight As Long
		//lfWidth As Long
		//lfEscapement As Long
		//lfOrientation As Long
		//lfWeight As Long
		//lfItalic As Byte
		//lfUnderline As Byte
		//lfStrikeOut As Byte
		//lfCharSet As Byte
		//lfOutPrecision As Byte
		//lfClipPrecision As Byte
		//lfQuality As Byte
		//lfPitchAndFamily As Byte
		//lfFaceName As String * 32
		//End Type
		System.out.println("lfHeight: "+annotation.getFont().getHeight());
		System.out.println("lfWidth: "+ annotation.getFont().getWidth());
		System.out.println("lfEscapement: "+annotation.getFont().getEscapement());
		System.out.println("lfOrientation: "+annotation.getFont().getOrientation());
		System.out.println("lfWeight: "+annotation.getFont().getWeight());
		System.out.println("ltalic: "+annotation.getFont().getItalic());
		System.out.println("Underline: "+annotation.getFont().getUnderline());
		System.out.println("Strikeout: "+annotation.getFont().getStrikeout());
		System.out.println("Charset: "+annotation.getFont().getCharset());
		System.out.println("OutPrecision: "+annotation.getFont().getOutPrecision());
		System.out.println("ClipPrecision: "+annotation.getFont().getClipPrecision());
		System.out.println("Quality: "+annotation.getFont().getQuality());
		System.out.println("PitchAndFamil: "+annotation.getFont().getPitchAndFamily());
		System.out.println("lFace: "+annotation.getFont().getFaceName());

		
		//DWORD bReserved3;                        // Reserved; must be set to 0.
		System.out.println("reserved3: " +annotation.getReserved3());

		//time_t Time;                                    // The time that the mark was first saved, in seconds, from
		                                                                // 00:00:00 1-1-1970 GMT. Every annotation mark has
		                                                                // time as one of its attributes. If you do not set the time before
		                                                                // the file is saved, the time is set to the date and time that the
		                                                                // save was initiated. This time is in the form returned by the
		                                                                // "time" C call, which is the number of seconds since
		                                                                // midnight 00:00:00 on 1-1-1970 GMT. If necessary, refer
		                                                                // to your C documentation for a more detailed description.
		System.out.println("time: " +annotation.getTime());

		//BOOL bVisible;                                // TRUE ¾ The mark is currently set to be visible.
		                                                                // Annotation marks can be visible or hidden.
		System.out.println("visible: " +annotation.getVisible());

		//DWORD dwReserved4;                // Reserved; must be set to 0x0FF83F.

		System.out.println("reserved4: " +annotation.getReserved4());

		//long lReserved[10];                      // Must be set to 0. 
		System.out.println("reserved10: " +annotation.getReserved10());

		System.out.println("W 1:"+buffer.getInt());
		System.out.println("W 2:"+buffer.getInt());
		System.out.println("W 3:"+buffer.getInt());
		
		System.out.println("Start Position: "+nPosition + " current Position ==>"+ buffer.position() +" diff: " +(buffer.position() - nPosition) +" should be ("+blockSize+")");
		
		return annotation;
	}

	
	private String get8ByteName(ByteBuffer buffer) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int value = buffer.get() & 0xff;
			sb.append(Character.toChars(value));
		}
		return sb.toString().trim();
	}

	public String readChar(ByteBuffer buffer, int size) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int value = buffer.get() & 0xff;
			sb.append(Character.toChars(value));
		}
		return sb.toString().trim();

	}
}
