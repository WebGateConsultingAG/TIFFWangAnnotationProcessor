package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
			WangAnnotation annotation = buildAnnoation(buffer, blockType, blockSize);
			if (annotation == null) {
				System.out.println("Stopped processing");
				break;
			}
			container.addAnnoation(annotation);
		}

		return container;
	}

	private WangAnnotation buildAnnoation(ByteBuffer buffer, int blockType, int blockSize) {
		switch (blockType) {
		case 2:
			return processStandardType(buffer, blockSize);
		default:
			System.out.println("no strategie for: "+ blockType);
		}
		return null;
	}

	private WangAnnotation processStandardType(ByteBuffer buffer, int blockSize) {

		String name = get8ByteName(buffer);
		int innerSize = buffer.getInt();
		WangAnnotation annotation = AnnotationFactory.getAnnotationByName(name);
		annotation.deserialize(this, buffer, innerSize);
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
