package biz.webgate.tools.tiffwangannotation;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
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
		Byte[] intbyte = new Byte[4];
		int i=0;
		i= ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(container.getHeader()).array(),intbyte,i);
		
		for(Byte b : intbyte){
			annoList.add(b);
		}
		i=0;
		i= ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(container.isWin32()?1:0).array(),intbyte,i);		
		for(Byte b : intbyte){
			annoList.add(b);
		}

		for(IAnnotation ia : container.getAnnotations()){
			fillAnnotation(ia,annoList);
			
		}
		byte[] retBytes =  new byte[annoList.size()];
		int j = 0;
		for(byte b : annoList){
			retBytes[j] = b;
			j++;
		}
		return retBytes;
	}
	
	private void fillAnnotation(IAnnotation ia,ArrayList<Byte> annoList){
		Byte[] intbyte = new Byte[4];
		int i=0;
		i= ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(ia.getBlockType()).array(),intbyte,i);		
		for(Byte b : intbyte){
			annoList.add(b);
		}
		i=0;
		i= ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(ia.getBlockSize()).array(),intbyte,i);		
		for(Byte b : intbyte){
			annoList.add(b);
		}
		if(ia.getBlockType()!=5){
			byte[] textBytes = ia.getAnnotationName().getBytes(Charset.forName("ISO_8859_1"));
			byte[] textBytesBlock = new byte[8];
			//fill up for 8bit
			for(int j = 0;j<8;j++){
				if(j<textBytes.length){
					textBytesBlock[j] = textBytes[j];
				}else{
					textBytesBlock[j] = 0;
				}
			}		
			for(Byte b : textBytesBlock){
				annoList.add(b);
			}
		
			i=0;
			i= ParseTools.reverseBListIncrease(ByteBuffer.allocate(4).putInt(ia.getInnerSize()).array(),intbyte,i);		
			for(Byte b : intbyte){
				annoList.add(b);
			}
		}
		
		
		Byte[] anoBytes = ia.serialize();	
		for(Byte b : anoBytes){
			annoList.add(b);
		}
		
		for(IAnnotation ia2 : ia.getAnnotations()){
			fillAnnotation(ia2,annoList);
		}
			
		
	}
	
	private IAnnotation buildAnnoation(ByteBuffer buffer, int blockType, int blockSize) {
		
		switch (blockType) {
		case 2:
			return processStandardType(buffer, blockSize,blockType);
		case 5:
			return processType5Annotation(buffer, blockSize);
		case 6:
			return processStandardType(buffer, blockSize,blockType);
		default:
			System.out.println("no strategie for: " + blockType);
		}
		return null;
	}


	private IAnnotation processStandardType(ByteBuffer buffer, int blockSize,int blockType) {
		String name = get8ByteName(buffer);
		int innerSize = buffer.getInt();
		IAnnotation annotation = AnnotationFactory.getAnnotationByName(name);
		if (annotation == null) {
			return null;
		}
		annotation.setBlockType(blockType);
		annotation.deserialize(this, buffer, innerSize);
		return annotation;
	}
	private IAnnotation processType5Annotation(ByteBuffer buffer, int blockSize) {
		Type5Annotation annotation = new Type5Annotation();
		annotation.deserialize(this, buffer, blockSize);
		annotation.setBlockType(5);
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
