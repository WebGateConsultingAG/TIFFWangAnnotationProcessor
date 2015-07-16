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
		IAnnotation previousAnnotation = null;
		while (buffer.hasRemaining()) {
			int blockType = buffer.getInt();
			int blockSize = buffer.getInt();
			IAnnotation annotation = buildAnnoation(buffer, blockType, blockSize);
			if (annotation == null) {
				System.out.println("Stopped processing");
				break;
			}
			if (blockType == 6) {
				previousAnnotation.addAnnotation(annotation);
			} else {
				container.addAnnoation(annotation);
				previousAnnotation = annotation;
			}
		}

		return container;
	}

	private IAnnotation buildAnnoation(ByteBuffer buffer, int blockType, int blockSize) {
		switch (blockType) {
		case 2:
			return processStandardType(buffer, blockSize);
		case 5:
			return processType5Annotation(buffer, blockSize);
		case 6:
			return processStandardType(buffer, blockSize);
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
		Type5Annotation annotation = new Type5Annotation();
		annotation.deserialize(this, buffer, blockSize);
		
		System.out.println("Font.lfHeight: "+annotation.getFont().getHeight());
		System.out.println("Font.lfWidth: "+ annotation.getFont().getWidth());
		System.out.println("Font.lfEscapement: "+annotation.getFont().getEscapement());
		System.out.println("Font.lfOrientation: "+annotation.getFont().getOrientation());
		System.out.println("Font.lfWeight: "+annotation.getFont().getWeight());
		System.out.println("Font.ltalic: "+annotation.getFont().getItalic());
		System.out.println("Font.Underline: "+annotation.getFont().getUnderline());
		System.out.println("Font.Strikeout: "+annotation.getFont().getStrikeout());
		System.out.println("Font.Charset: "+annotation.getFont().getCharset());
		System.out.println("Font.OutPrecision: "+annotation.getFont().getOutPrecision());
		System.out.println("Font.ClipPrecision: "+annotation.getFont().getClipPrecision());
		System.out.println("Font.Quality: "+annotation.getFont().getQuality());
		System.out.println("Font.PitchAndFamil: "+annotation.getFont().getPitchAndFamily());
		System.out.println("Font.lFace: "+annotation.getFont().getFaceName());
		
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
			int value =buffer.get() & 0xff;
			sb.append(Character.toChars(value));
		}
		return sb.toString().trim();

	}
}
