package tree;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import demo.MyAnnotation;

@MyAnnotation
public class AnnotatedTest {

	@Ignore @Test
	public final void testAnnotationNameMatches() {
		fail("Not yet implemented");
	}

	@Test
	public final void testAnnotationClassName() {
		assertEquals("demo.MyAnnotation",
			Annotated.annotationClassName(
				getClass().getDeclaredAnnotations()[0]));
	}

	@Ignore @Test
	public final void testIsAnnotated() {
		// fail("Not yet implemented");
	}

}
