package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import biz.webgate.tools.tiffwangannotation.annotations.OiAnTextAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiAnoDatAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiGroupAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiHiliteAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiIndexAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiModNmAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiOwnNmAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.Type5Annotation;
import biz.webgate.tools.tiffwangannotation.annotations.helpers.Area;
import biz.webgate.tools.tiffwangannotation.annotations.helpers.LogFont;
import biz.webgate.tools.tiffwangannotation.annotations.helpers.Point;
import biz.webgate.tools.tiffwangannotation.annotations.helpers.RGBColor;
import static org.junit.Assert.*;
public class AnnotationWritingTest {
	
	@Test 
	public void testOiAnoDatSerialize() throws IOException{
		OiAnoDatAnnotation ia1 = new OiAnoDatAnnotation();
		ia1.setMaxPoints(2);
		ia1.setPointCount(2);
		List<Point> points = new ArrayList<Point>();
		Point p1 = new Point(0,90);
		Point p2 = new Point(80,0);
		points.add(p1);
		points.add(p2);
		ia1.setPoints(points);
		Byte[] byteList = ia1.serialize();
		assertNotNull(byteList);
		byte[] bytes = new byte[byteList.length];
		int i=0;
		for(Byte b : byteList){
			bytes[i] = b;
			i++;
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		OiAnoDatAnnotation ia2 = new OiAnoDatAnnotation();
		ia2.deserialize(null, buffer, bytes.length);
		assertEquals(ia1.getPointCount() , ia2.getPointCount());
		assertEquals(ia1.getMaxPoints() , ia2.getMaxPoints());
		assertEquals(ia1.getPoints().get(0).getX() , ia2.getPoints().get(0).getX());
		assertEquals(ia1.getPoints().get(0).getY() , ia2.getPoints().get(0).getY());
		assertEquals(ia1.getPoints().get(1).getX() , ia2.getPoints().get(1).getX());
		assertEquals(ia1.getPoints().get(1).getY() , ia2.getPoints().get(1).getY());
	}
	
	@Test
	public void testOiAnTextSerialize() throws IOException{
		OiAnTextAnnotation oi1 = new OiAnTextAnnotation();
		oi1.setAnoTextLenght(6);
		oi1.setText("Völler");
		oi1.setCreationScale(3);
		oi1.setCurrentOrientation(1);
		oi1.setReserved1(0);
		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes = new byte[byteList.length];
		int i=0;
		for(Byte b : byteList){
			bytes[i] = b;
			i++;
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		OiAnTextAnnotation oi2 = new OiAnTextAnnotation();
		oi2.deserialize(null, buffer, bytes.length);
		assertEquals(oi1.getText(),oi2.getText());
		assertEquals(oi1.getAnoTextLenght(),oi2.getAnoTextLenght());
		assertEquals(oi1.getCreationScale(),oi2.getCreationScale());
		assertEquals(oi1.getCurrentOrientation(),oi2.getCurrentOrientation());
		assertEquals(oi1.getReserved1(),oi2.getReserved1());
	}
	
	@Test
	public void testOiGroupSerialize() throws IOException{
		OiGroupAnnotation oi1 = new OiGroupAnnotation();
		oi1.setName("Müller");
		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes = new byte[byteList.length];
		int i=0;
		for(Byte b : byteList){
			bytes[i] = b;
			i++;
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		OiGroupAnnotation oi2 = new OiGroupAnnotation();
		oi2.deserialize(null, buffer, bytes.length);
		assertEquals(oi1.getName(),oi2.getName());
	}
	
	@Test
	public void testOiHiliteSerialize() throws IOException{
		OiHiliteAnnotation oi1 = new OiHiliteAnnotation();
		byte[] init = new byte[] {'1','2','1','5'};
		oi1.setValue(init);
		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes = new byte[byteList.length];
		int i=0;
		for(Byte b : byteList){
			bytes[i] = b;
			i++;
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		OiHiliteAnnotation oi2 = new OiHiliteAnnotation();
		oi2.deserialize(null, buffer, bytes.length);
		for(int j = 0; j<oi1.getValue().length;j++ ){
			assertEquals(oi1.getValue()[j],oi2.getValue()[j]);
		}
	}
	
	@Test
	public void testOiIndex() throws IOException{
		OiIndexAnnotation oi1 = new OiIndexAnnotation();
		oi1.setName("Möller");
		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes = new byte[byteList.length];
		int i=0;
		for(Byte b : byteList){
			bytes[i] = b;
			i++;
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		OiIndexAnnotation oi2 = new OiIndexAnnotation();
		oi2.deserialize(null, buffer, bytes.length);
		assertEquals(oi1.getName(),oi2.getName());
	}
	
	
	@Test
	public void testOiModNm() throws IOException{
		OiModNmAnnotation oi1 = new OiModNmAnnotation();
		oi1.setName("Möller");
		
		long time = new Date().getTime()/1000;
		time = time * 1000;
		oi1.setDate(new Date(time));
		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes = new byte[byteList.length];
		int i=0;
		for(Byte b : byteList){
			bytes[i] = b;
			i++;
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		OiModNmAnnotation oi2 = new OiModNmAnnotation();
		oi2.deserialize(null, buffer, bytes.length);
		assertEquals(oi1.getName(),oi2.getName());
		assertEquals(oi1.getDate().getTime(),oi2.getDate().getTime());
	}
	
	@Test
	public void testOiOvnNm() throws IOException{
		OiOwnNmAnnotation oi1 = new OiOwnNmAnnotation();
		oi1.setName("Möller");
		oi1.setDate(new Date());
		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes = new byte[byteList.length];
		int i=0;
		for(Byte b : byteList){
			bytes[i] = b;
			i++;
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		OiOwnNmAnnotation oi2 = new OiOwnNmAnnotation();
		oi2.deserialize(null, buffer, bytes.length);
		assertEquals(oi1.getName(),oi2.getName());
		assertEquals(oi1.getDate(),oi2.getDate());
	}
	
	
	@Test
	public void testTyp5() throws IOException{
		Type5Annotation oi1 = new Type5Annotation();
		oi1.setType(1);
		oi1.setArea(new Area(5,5,100,100));
		oi1.setColor1(new RGBColor(50, 50, 50, 200));
		oi1.setColor2(new RGBColor(150, 150, 150, 200));
		oi1.setHighligt(1);
		oi1.setTransparent(1);
		oi1.setLineSize(12);
		oi1.setReserved1(0);
		oi1.setReserved2(0);
		oi1.setReserved3(0);
		oi1.setReserved4(0);
		oi1.setReserved10(0);
		LogFont f = new LogFont();
		f.setHeight(12);
		f.setFaceName("Arial");
		oi1.setFont(f);
		oi1.setTime(50);
		oi1.setVisible(1);

		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes = new byte[byteList.length];
		int i=0;
		for(Byte b : byteList){
			bytes[i] = b;
			i++;
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		Type5Annotation oi2 = new Type5Annotation();
		oi2.deserialize(null, buffer, bytes.length);
		assertEquals(oi1.getColor1(),oi2.getColor1());
		assertEquals(oi1.getColor2(),oi2.getColor2());
		assertEquals(oi1.getReserved1(),oi2.getReserved1());
		assertEquals(oi1.getReserved2(),oi2.getReserved2());
		assertEquals(oi1.getReserved3(),oi2.getReserved3());
		assertEquals(oi1.getReserved4(),oi2.getReserved4());
		assertEquals(oi1.getReserved10(),oi2.getReserved10());
		assertEquals(oi1.getArea(),oi2.getArea());
		assertEquals(oi1.getHighligt(),oi2.getHighligt());
		assertEquals(oi1.getFont(),oi2.getFont());
		assertEquals(oi1.getLineSize(),oi2.getLineSize());
		assertEquals(oi1.getTime(),oi2.getTime());
		assertEquals(oi1.getType(),oi2.getType());
		assertEquals(oi1.getTransparent(),oi2.getTransparent());
		assertEquals(oi1.getAnnotations(),oi2.getAnnotations());
		
	}
	
	
	
}
