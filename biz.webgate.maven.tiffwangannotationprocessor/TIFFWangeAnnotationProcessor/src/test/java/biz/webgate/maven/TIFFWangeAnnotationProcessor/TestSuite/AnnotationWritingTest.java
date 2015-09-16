package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import org.junit.Test;

import com.sun.media.imageio.plugins.tiff.TIFFDirectory;
import com.sun.media.imageio.plugins.tiff.TIFFField;

import biz.webgate.tools.tiffwangannotation.IAnnotation;
import biz.webgate.tools.tiffwangannotation.WangAnnotationContainer;
import biz.webgate.tools.tiffwangannotation.WangAnnotationParser;
import biz.webgate.tools.tiffwangannotation.annotations.OiAnoDatAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.helpers.Point;

public class AnnotationWritingTest {
	
	@Test
	public void testOiAnoDatAnnotationserialize() throws IOException{
		WangAnnotationContainer annotationContainer = getcontainer();
		for(IAnnotation ia :  annotationContainer.getAnnotations()){
			
		}
		
		
	}
	
	public WangAnnotationContainer getcontainer() throws IOException{
		URL url = getURLforTestFile(AbstractPictureTestBase.PIC_IMGVIEWER_ANNOTATION_TEXTAREA);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
		System.out.println(wangAnnotations.getCount());
		System.out.println(wangAnnotations.getAsBytes().length);
		// System.out.println(wangAnnotations.getAsInt(1));
		//printAsByte(wangAnnotations.getAsBytes());
		WangAnnotationContainer parsedAnnotations = WangAnnotationParser.INSTANCE.parse((byte[]) wangAnnotations.getData());
		assertNotNull(parsedAnnotations);
		return parsedAnnotations;
	}
	
	protected URL getURLforTestFile(String testFile) {
		URL url = getClass().getResource("/" + testFile);
		assertNotNull("Testfile is missing", url);
		return url;
	}
	
	public List<PictureDetail> readPictureFromUrl(URL url) throws IOException {
		List<PictureDetail> details = new ArrayList<PictureDetail>();
		ImageInputStream stream;
		Iterator<ImageReader> readers = ImageIO.getImageReadersBySuffix("tif");
		ImageReader tiffReader = (ImageReader) readers.next();
		InputStream is = url.openStream();
		stream = ImageIO.createImageInputStream(is);
		try {
			tiffReader.setInput(stream);
		} catch (IllegalArgumentException e) {
			throw new IOException(e.toString());
		}
		int imageIndex = 0;
		do {
			BufferedImage image = null;
			IIOMetadata metaData = null;
			try {
				image = tiffReader.read(imageIndex, null);
				metaData = tiffReader.getImageMetadata(imageIndex);
			} catch (IndexOutOfBoundsException e) {
				// Happens when imageIndex == number of pages in stream
				break;
			}
			details.add(new PictureDetail(image, metaData));
			stream.flush(); // Discard cached data up to current position
			imageIndex++; // Go to the next image.
		} while (true);
		return details;
	}
	
}
