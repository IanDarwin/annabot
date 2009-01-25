package annabot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tree.MethodAnnotated;
import tree.Operator;
import tree.RequiresOne;
import demo.MyAnnotatedClass;
import demo.MyAnnotation;


public class ProcessTest {
	
	Operator[] checks = {
		new RequiresOne(
			new MethodAnnotated(MyAnnotation.class))
	};
	Operator[] failingChecks = {
		new RequiresOne(
			new MethodAnnotated(MyAnnotation.class))
	};
	
	@Test
	public void testOneArgConstructor() {
		Processor p = new Processor(checks);
		assertTrue(
			p.process(MyAnnotatedClass.class));
	}
	
	@Test
	public void testFailingOneArgConstructor() {
		Processor p = new Processor(failingChecks);
		assertFalse(
			p.process(Object.class));
	}
	@Test
	public void testNoArgConstructor() {
		Processor p = new Processor();
		assertTrue(
			p.process(MyAnnotatedClass.class, checks));
	}
	@Test(expected=IllegalStateException.class)
	public void testFailureNoArgConstructor() {
		Processor p = new Processor();
		p.process(MyAnnotatedClass.class);
	}
}
