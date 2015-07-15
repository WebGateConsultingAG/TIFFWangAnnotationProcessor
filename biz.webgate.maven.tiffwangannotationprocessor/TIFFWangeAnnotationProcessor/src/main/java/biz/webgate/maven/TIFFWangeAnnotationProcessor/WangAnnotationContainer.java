package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import java.util.ArrayList;
import java.util.List;

public class WangAnnotationContainer {

	private final int header;
	private final boolean win32;
	private final List<WangAnnotation> annotations = new ArrayList<WangAnnotation>();

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

	public List<WangAnnotation> getAnnotations() {
		return annotations;
	}

	public void addAnnoation(WangAnnotation annotation) {
		annotations.add(annotation);
	}

}
