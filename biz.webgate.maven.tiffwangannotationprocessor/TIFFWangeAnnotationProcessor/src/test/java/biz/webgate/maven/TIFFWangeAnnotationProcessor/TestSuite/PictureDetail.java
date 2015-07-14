package biz.webgate.maven.TIFFWangeAnnotationProcessor.TestSuite;

import java.awt.image.BufferedImage;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;

import com.sun.media.imageio.plugins.tiff.TIFFDirectory;

public class PictureDetail {

	private final BufferedImage image;
	private final IIOMetadata metadata;

	public PictureDetail(BufferedImage image, IIOMetadata metadata) {
		super();
		this.image = image;
		this.metadata = metadata;
	}

	public BufferedImage getImage() {
		return image;
	}

	public IIOMetadata getMetadata() {
		return metadata;
	}

	public TIFFDirectory getTIFFDirectory() throws IIOInvalidTreeException {
		return TIFFDirectory.createFromMetadata(metadata);
	}

}
