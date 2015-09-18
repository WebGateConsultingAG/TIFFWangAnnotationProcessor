package biz.webgate.tools.tiffwangannotation;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.metadata.IIOMetadata;

public class WangAnnotationContainer {

	private final int header;
	private final boolean win32;
	private final List<IAnnotation> annotations = new ArrayList<IAnnotation>();
	private IIOMetadata metadata;
	public WangAnnotationContainer(int header, boolean win32) {
		super();
		this.header = header;
		this.win32 = win32;
	}

	public int getHeader() {
		return header;
	}

	public boolean isWin32() {
		return win32;
	}

	public List<IAnnotation> getAnnotations() {
		return annotations;
	}

	public void addAnnoation(IAnnotation annotation) {
		annotations.add(annotation);
	}

	public static WangAnnotationContainer buildContainer(int header2, int win32) {
		return new WangAnnotationContainer(header2, win32 == 1);
	}

	public IIOMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(IIOMetadata metadata) {
		this.metadata = metadata;
	}
	
}
