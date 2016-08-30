package tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import demo.MyAnnotation;

@MyAnnotation
public class ClassAnnotatedTest {

	@Test public void testTrueWithStringClassName() {
		Operator t = new ClassAnnotated("demo.MyAnnotation");
		assertTrue(t.process(ClassAnnotatedTest.class));
	}
	@Test public void testFalseWithStringClassName() {
		Operator t = new ClassAnnotated("foo.Goo");
		assertFalse(t.process(ClassAnnotatedTest.class));
	}

	@Test public void testTrueWithStringPackageRef() {
		Operator t = new ClassAnnotated("demo.*");
		assertTrue(t.process(ClassAnnotatedTest.class));
	}
	
	@Test public void testFalseWithStringPackageRef() {
		Operator t = new ClassAnnotated("dummy.*");
		assertFalse(t.process(ClassAnnotatedTest.class));
	}

	@Test public void testTrueWithAnnotationClass() {
		Operator t = new ClassAnnotated(MyAnnotation.class);
		assertTrue(t.process(ClassAnnotatedTest.class));
	}

	@Test public void testFalseWithAnnotationClass() {
		Operator t = new ClassAnnotated(java.lang.SuppressWarnings.class);
		assertFalse(t.process(Object.class));
	}
}
