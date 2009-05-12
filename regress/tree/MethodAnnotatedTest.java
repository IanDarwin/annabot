package tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import demo.MyAnnotation;

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
	
	@MyAnnotation
	@Test public void doTestTrueWithAnnoClassAndMethodPatt() {
		Operator t =
			new MethodAnnotated(MyAnnotation.class, "doT*");
		assertTrue(t.process(getClass()));
	}
	
	@Test public void doTestFalseWithAnnoClassAndMethodPatt() {
		Operator t =
			new MethodAnnotated(MyAnnotation.class, "fredzz");
		assertFalse(t.process(getClass()));
	}
}
