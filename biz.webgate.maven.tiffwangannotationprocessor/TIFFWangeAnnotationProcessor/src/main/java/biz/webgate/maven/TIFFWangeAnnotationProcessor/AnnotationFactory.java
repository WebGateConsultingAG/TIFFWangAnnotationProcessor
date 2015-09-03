package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.OiAnTextAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.OiAnoDatAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.OiGroupAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.OiHiliteAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.OiIndexAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.annotations.OiOwnNmAnnotation;

public class AnnotationFactory {

	public static IAnnotation getAnnotationByName(String name) {
		if ("OiOwnNm".equals(name)) {
			return new OiOwnNmAnnotation();
		}
		if ("OiModNm".equals(name)) {
			return new OiOwnNmAnnotation();
		}
		if ("OiGroup".equals(name)) {
			return new OiGroupAnnotation();
		}
		if ("OiIndex".equals(name)) {
			return new OiIndexAnnotation();
		}
		if ("OiAnText".equals(name)) {
			return new OiAnTextAnnotation();
		}
		if ("OiHilite".equals(name)) {
			return new OiHiliteAnnotation();
		}
		if ("OiAnoDat".equals(name)) {
			return new OiAnoDatAnnotation();
		}
		System.out.println("No Annotation found for: " + name);
		return null;
	}

}
