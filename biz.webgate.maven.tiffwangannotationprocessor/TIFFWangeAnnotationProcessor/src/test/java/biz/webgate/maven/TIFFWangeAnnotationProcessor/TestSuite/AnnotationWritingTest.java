package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import biz.webgate.tools.tiffwangannotation.ParseTools;
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
		byte[] bytes =  ParseTools.createFromByteObect(byteList);
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
		byte[] bytes =  ParseTools.createFromByteObect(byteList);
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
		byte[] bytes =  ParseTools.createFromByteObect(byteList);
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
		byte[] bytes =  ParseTools.createFromByteObect(byteList);
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
		byte[] bytes =  ParseTools.createFromByteObect(byteList);
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
		oi1.setName("Hunzenack");
		long time = new Date().getTime()/1000;
		time = time * 1000;
		oi1.setDate(new Date(time));
		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes =  ParseTools.createFromByteObect(byteList);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		OiOwnNmAnnotation oi2 = new OiOwnNmAnnotation();
		oi2.deserialize(null, buffer, bytes.length);
		assertEquals(oi1.getName(),oi2.getName());
		assertEquals(oi1.getDate().getTime(),oi2.getDate().getTime());
	}
	
	
	@Test
	public void testTyp5() throws IOException{
		Type5Annotation oi1 = new Type5Annotation();
		oi1.setType(1);
		oi1.setArea(new Area(10,5,100,105));
		oi1.setColor1(new RGBColor(10, 20, 30, 1));
		oi1.setColor2(new RGBColor(15, 25, 35, 1));
		oi1.setHighligt(1);
		oi1.setTransparent(1);
		oi1.setLineSize(12);
		oi1.setReserved1(0);
		oi1.setReserved2(0);
		oi1.setReserved3(0);
		oi1.setReserved4(0);
		oi1.setReserved10(0);
		LogFont f = createFont();
		oi1.setFont(f);
		oi1.setTime(50);
		oi1.setVisible(1);

		Byte[] byteList = oi1.serialize();
		assertNotNull(byteList);
		byte[] bytes =  ParseTools.createFromByteObect(byteList);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		Type5Annotation oi2 = new Type5Annotation();
		oi2.deserialize(null, buffer, bytes.length);		
		assertEquals(oi1.getType(),oi2.getType());
		assertEquals(oi1.getArea().getBottomX(),oi2.getArea().getBottomX());
		assertEquals(oi1.getColor1().getBlue(),oi2.getColor1().getBlue());
		assertEquals(oi1.getColor2().getRed(),oi2.getColor2().getRed());
		assertEquals(oi1.getHighligt(),oi2.getHighligt());
		assertEquals(oi1.getTransparent(),oi2.getTransparent());
		assertEquals(oi1.getLineSize(),oi2.getLineSize());
		assertEquals(oi1.getReserved1(),oi2.getReserved1());
		assertEquals(oi1.getReserved2(),oi2.getReserved2());
		assertEquals(oi1.getReserved3(),oi2.getReserved3());
		assertEquals(oi1.getReserved4(),oi2.getReserved4());
		assertEquals(oi1.getTime(),oi2.getTime());
		//assertEquals(oi1.getAnnotations(),oi2.getAnnotations());
		assertEquals(oi1.getReserved10(),oi2.getReserved10());
		assertEquals(oi1.getFont().getFaceName(),oi2.getFont().getFaceName());
		assertEquals(oi1.getFont().getWeight(),oi2.getFont().getWeight());

	}
	@Test
	public void rgbColorTest(){
		RGBColor rgbc = new RGBColor(60,50,120,1);
		byte[] asbyte = rgbc.getAsByteArray();
		ByteBuffer bb = ByteBuffer.wrap(asbyte);
		RGBColor rgbc2 = RGBColor.buildColor(bb);
		assertEquals(rgbc.getBlue(), rgbc2.getBlue());
	}
	@Test
	public void LogfontTest(){
		LogFont font = createFont();
		
		byte[] fontBytes = font.getAsByteArray();
		
		ByteBuffer bb = ByteBuffer.wrap(fontBytes);
		LogFont font2 = LogFont.buildLogFont(null, bb);
		assertEquals(font.getFaceName(),font2.getFaceName());
		assertEquals(font.getHeight(),font2.getHeight());
		assertEquals(font.getWeight(),font2.getWeight());
		assertEquals(font.getItalic(),font2.getItalic());
		
	}
	@Test
	public void areaTest(){
		Area area = new Area(10,5,100,105);
		byte[] areabytes = area.getAsByteArray();
		ByteBuffer bb = ByteBuffer.wrap(areabytes);
		Area area2 = Area.buildArea(bb);
		assertEquals(area.getBottomX(),area2.getBottomX());
		assertEquals(area.getBottomY(),area2.getBottomY());
		assertEquals(area.getTopX(),area2.getTopX());
		assertEquals(area.getTopY(),area2.getTopY());
	}
	
	private LogFont createFont(){
		LogFont font = new LogFont();
		font.setHeight(12);
		font.setWidth(10);
		font.setEscapement(12);
		long escape = 15;
		font.setOrientation(escape);
		long weight = 12;
		font.setWeight(weight);
		font.setFaceName("Arial");
		font.setItalic((byte)0);
		font.setUnderline((byte)0);
		font.setStrikeout((byte)0);
		font.setCharset((byte)0);
		font.setOutPrecision((byte)0);
		font.setClipPrecision((byte)0);
		font.setQuality((byte)0);
		font.setPitchAndFamily((byte)0);
		return font;
	}
}
