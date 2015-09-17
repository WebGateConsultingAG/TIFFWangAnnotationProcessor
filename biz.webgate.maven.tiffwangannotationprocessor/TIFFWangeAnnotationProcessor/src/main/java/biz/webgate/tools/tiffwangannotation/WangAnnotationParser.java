package biz.webgate.tools.tiffwangannotation;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import biz.webgate.tools.tiffwangannotation.annotations.Type5Annotation;


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

	public byte[] writeannotation(WangAnnotationContainer container){
		ArrayList<Byte> annoList = new ArrayList<Byte>();
		byte[] header = ByteBuffer.allocate(4).putInt(container.getHeader()).array();
		for(Byte b : header){
			annoList.add(b);
		}
		byte[] win32 = ByteBuffer.allocate(4).putInt(container.isWin32()?1:0).array();
		for(Byte b : win32){
			annoList.add(b);
		}
		
		for(IAnnotation ia : container.getAnnotations()){
			Byte[] anoBytes = ia.serialize();
			for(Byte b : anoBytes){
				annoList.add(b);
			}
		}
		byte[] retBytes =  new byte[annoList.size()];
		int i = 0;
		for(byte b : annoList){
			retBytes[i] = b;
			i++;
		}
		return retBytes;
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
		if (annotation == null) {
			return null;
		}
		annotation.deserialize(this, buffer, innerSize);
		return annotation;
	}
	private IAnnotation processType5Annotation(ByteBuffer buffer, int blockSize) {
		Type5Annotation annotation = new Type5Annotation();
		annotation.deserialize(this, buffer, blockSize);

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
