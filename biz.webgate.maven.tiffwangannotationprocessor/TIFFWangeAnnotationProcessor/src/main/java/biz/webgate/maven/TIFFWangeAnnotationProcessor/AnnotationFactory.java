package biz.webgate.maven.TIFFWangeAnnotationProcessor;

import biz.webgate.maven.TIFFWangeAnnotationProcessor.Annotations.OiOwnNmAnnotation;

public class AnnotationFactory {

	public static WangAnnotation getAnnotationByName(String name) {
		if ("OiOwnNm".equals(name)) {
			return new OiOwnNmAnnotation();
		}
		if ("OiModNm".equals(name)) {
			return new OiOwnNmAnnotation();
		}
		System.out.println("No Annotation found for: "+ name);
		return null;
	}

}
