package biz.webgate.tools.tiffwangannotation;

import biz.webgate.tools.tiffwangannotation.annotations.OiAnTextAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiAnoDatAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiGroupAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiHiliteAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiIndexAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiInitAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiModNmAnnotation;
import biz.webgate.tools.tiffwangannotation.annotations.OiOwnNmAnnotation;


public class AnnotationFactory {

	public static IAnnotation getAnnotationByName(String name) {
		if ("OiOwnNm".equals(name)) {
			return new OiOwnNmAnnotation();
		}
		if ("OiModNm".equals(name)) {
			return new OiModNmAnnotation();
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
		if ("OiInitls".equals(name)){
			return new OiInitAnnotation();
		}
		
		System.out.println("No Annotation found for: " + name);
		return null;
	}

}
