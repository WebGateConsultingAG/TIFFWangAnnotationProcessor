package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.Annotations.OiGroupAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.Annotations.OiIndexAnnotation;
import biz.webgate.maven.TIFFWangeAnnotationProcessor.Annotations.OiOwnNmAnnotation;

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
		System.out.println("No Annotation found for: " + name);
		return null;
	}

}
