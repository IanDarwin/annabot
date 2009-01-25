package tree;

import org.junit.Test;

import demo.MyAnnotation;
import static org.junit.Assert.*;

public class MethodAnnotatedTest {

	@MyAnnotation
	@Test public void testTrueWithStringClassName() {
		Operator t = new MethodAnnotated("demo.MyAnnotation");
		assertTrue(t.process(MethodAnnotatedTest.class));
	}
	
	@Test public void testFalseWithStringClassName() {
		Operator t = new MethodAnnotated("foo.Goo");
		assertFalse(t.process(MethodAnnotatedTest.class));
	}

	@Test public void testTrueWithStringPackageRef() {
		Operator t = new MethodAnnotated("demo.*");
		assertTrue(t.process(MethodAnnotatedTest.class));
	}
	
	@Test public void testFalseWithStringPackageRef() {
		Operator t = new MethodAnnotated("dummy.*");
		assertFalse(t.process(MethodAnnotatedTest.class));
	}

	@Test public void testTrueWithAnnotationClass() {
		Operator t = new MethodAnnotated(MyAnnotation.class);
		assertTrue(t.process(MethodAnnotatedTest.class));
	}

	@Test public void testFalseWithAnnotationClass() {
		Operator t = new MethodAnnotated(java.lang.SuppressWarnings.class);
		assertFalse(t.process(Object.class));
	}
}
