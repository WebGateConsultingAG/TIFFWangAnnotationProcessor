package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import com.sun.media.imageio.plugins.tiff.TIFFDirectory;

public class AbstractPictureTestBase {

	public static final String PIC_MIT_ANNOTATIONS_TEXTAREA = "one-annotation-textarea.tif";
	public static final String PIC_MIT_ANNOTATIONS_TEXTNOTE = "one-annotation-textnote.tif";

	public AbstractPictureTestBase() {
		super();
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