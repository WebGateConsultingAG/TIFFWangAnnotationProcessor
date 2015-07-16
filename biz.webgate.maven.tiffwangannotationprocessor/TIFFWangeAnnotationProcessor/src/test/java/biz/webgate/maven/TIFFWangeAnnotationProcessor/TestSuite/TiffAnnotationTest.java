package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotationContainer;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.WangAnnotationParser;

import com.sun.media.imageio.plugins.tiff.TIFFDirectory;
import com.sun.media.imageio.plugins.tiff.TIFFField;

public class TiffAnnotationTest extends AbstractPictureTestBase {

	private static final String EXTERNALFILE = "file:///D:/BitBucket/AWF/Testfiles/input/pic-mit-annotations.tif";
	@Test
	public void testAnnotations() throws IOException {

		URL url = getURLforTestFile(AbstractPictureTestBase.PIC_IMGVIEWER_ANNOTATION_TEXTAREA);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
	}

	@Test
	public void testParseAnnotationsAT() throws IOException {

		URL url = getURLforTestFile(AbstractPictureTestBase.PIC_IMGVIEWER_ANNOTATION_TEXTAREA);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
		System.out.println(wangAnnotations.getCount());
		System.out.println(wangAnnotations.getAsBytes().length);
		// System.out.println(wangAnnotations.getAsInt(1));
		printAsByte(wangAnnotations.getAsBytes());
		WangAnnotationContainer parsedAnnotations = WangAnnotationParser.INSTANCE.parse((byte[]) wangAnnotations.getData());
		assertNotNull(parsedAnnotations);

	}

	@Test
	public void testParseAnnotationsMulti() throws IOException {

		URL url = getURLforTestFile(AbstractPictureTestBase.PIC_IMGVIEWER_ANNOTATION_VARIOUS);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
		System.out.println(wangAnnotations.getCount());
		System.out.println(wangAnnotations.getAsBytes().length);
		// System.out.println(wangAnnotations.getAsInt(1));
		printAsByte(wangAnnotations.getAsBytes());
		WangAnnotationContainer parsedAnnotations = WangAnnotationParser.INSTANCE.parse((byte[]) wangAnnotations.getData());
		assertNotNull(parsedAnnotations);
	}

	@Test
	public void testParseAnnotationsMP() throws IOException {

		URL url = getURLforTestFile(AbstractPictureTestBase.PIC_MIT_ANNOTATIONS_TEXTAREA);
		List<PictureDetail> allPictures = readPictureFromUrl(url);
		TIFFDirectory tDir = allPictures.get(0).getTIFFDirectory();
		assertNotNull(tDir);
		TIFFField wangAnnotations = tDir.getTIFFField(32932);
		assertNotNull(wangAnnotations);
		System.out.println(wangAnnotations.getCount());
		System.out.println(wangAnnotations.getAsBytes().length);
		// System.out.println(wangAnnotations.getAsInt(1));
		printAsByte(wangAnnotations.getAsBytes());
		WangAnnotationContainer parsedAnnotations = WangAnnotationParser.INSTANCE.parse((byte[]) wangAnnotations.getData());
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
		WangAnnotationContainer parsedAnnotations = WangAnnotationParser.INSTANCE.parse((byte[]) wangAnnotations.getData());
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
