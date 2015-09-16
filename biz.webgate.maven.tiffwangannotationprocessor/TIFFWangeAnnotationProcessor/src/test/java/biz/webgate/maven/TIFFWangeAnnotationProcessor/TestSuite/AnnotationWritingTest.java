package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import biz.webgate.tools.tiffwangannotation.annotations.OiAnoDatAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.helpers.Point;
import static org.junit.Assert.*;
public class AnnotationWritingTest {
	
	@Test
	public void testOiAnoDatAnnotationserialize() throws IOException{
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
}
