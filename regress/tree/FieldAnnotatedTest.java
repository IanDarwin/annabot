package tree;

import org.junit.Test;

import demo.MyAnnotation;
import static org.junit.Assert.*;

public class FieldAnnotatedTest {

	@MyAnnotation
	public int i;
	
	@Test public void testTrueWithStringClassName() {
		Operator t = new FieldAnnotated("demo.MyAnnotation");
		assertTrue(t.process(FieldAnnotatedTest.class));
	}
	
	@Test public void testFalseWithStringClassName() {
		Operator t = new FieldAnnotated("foo.Goo");
		assertFalse(t.process(FieldAnnotatedTest.class));
	}

	@Test public void testTrueWithStringPackageRef() {
		Operator t = new FieldAnnotated("demo.*");
		assertTrue(t.process(FieldAnnotatedTest.class));
	}
	
	@Test public void testFalseWithStringPackageRef() {
		Operator t = new FieldAnnotated("dummy.*");
		assertFalse(t.process(FieldAnnotatedTest.class));
	}

	@Test public void testTrueWithAnnotationClass() {
		Operator t = new FieldAnnotated(MyAnnotation.class);
		assertTrue(t.process(FieldAnnotatedTest.class));
	}

	@Test public void testFalseWithAnnotationClass() {
		Operator t = new FieldAnnotated(java.lang.SuppressWarnings.class);
		assertFalse(t.process(Object.class));
	}
}
