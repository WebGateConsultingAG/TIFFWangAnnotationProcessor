package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.sun.media.imageio.plugins.tiff.TIFFDirectory;
import com.sun.media.imageio.plugins.tiff.TIFFField;

public class TiffAnnotationTest extends AbstractPictureTestBase {

	@Test
	public void testAnnotations() throws IOException {

		URL url = getURLforTestFile(AbstractPictureTestBase.PIC_MIT_ANNOTATIONS_TEXTAREA);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
	}
	
	@Test
	public void testParseAnnotations() throws IOException {

		URL url = getURLforTestFile(AbstractPictureTestBase.PIC_MIT_ANNOTATIONS_TEXTAREA);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
		System.out.println(wangAnnotations.getCount());
		System.out.println(wangAnnotations.getAsBytes().length);
		//System.out.println(wangAnnotations.getAsInt(1));
		List<WangAnnotation> parsedAnnotations = WangAnnotationParser.INSTANCE.parse((byte[]) wangAnnotations.getData());
		assertNotNull(parsedAnnotations);

	}
}
