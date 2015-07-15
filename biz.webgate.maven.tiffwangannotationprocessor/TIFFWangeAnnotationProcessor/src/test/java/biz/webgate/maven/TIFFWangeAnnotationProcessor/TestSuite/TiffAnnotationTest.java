package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.sun.media.imageio.plugins.tiff.TIFFDirectory;
import com.sun.media.imageio.plugins.tiff.TIFFField;

public class TiffAnnotationTest extends AbstractPictureTestBase {

	private static final String EXTERNALFILE = "file:///D:/BitBucket/AWF/Testfiles/input/pic-mit-annotations.tif";
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

		URL url = getURLforTestFile(AbstractPictureTestBase.PIC_MIT_ANNOTATIONS_TEXTNOTE);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
		System.out.println(wangAnnotations.getCount());
		System.out.println(wangAnnotations.getAsBytes().length);
		// System.out.println(wangAnnotations.getAsInt(1));
		printAsByte(wangAnnotations.getAsBytes());
		List<WangAnnotation> parsedAnnotations = WangAnnotationParser.INSTANCE.parse((byte[]) wangAnnotations.getData());
		assertNotNull(parsedAnnotations);

	}
	
	@Test
	@Ignore
	public void testExternalFile() throws IOException {

		URL url =new URL(EXTERNALFILE);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
		System.out.println(wangAnnotations.getCount());
		System.out.println(wangAnnotations.getAsBytes().length);
		// System.out.println(wangAnnotations.getAsInt(1));
		printAsByte(wangAnnotations.getAsBytes());
		List<WangAnnotation> parsedAnnotations = WangAnnotationParser.INSTANCE.parse((byte[]) wangAnnotations.getData());
		assertNotNull(parsedAnnotations);

	}

	private void printAsByte(byte[] datas) {
		int counter = 1;
		for (byte value : datas) {
			int numericValue = value & 0xff;
			System.out.format("%5d", numericValue);
			if (counter % 16 == 0) {
				System.out.println();
			}
			counter++;
		}
		System.out.println();
	}
	
}
