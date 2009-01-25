package annabot;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tree.MethodAnnotated;
import tree.Operator;
import tree.RequiresOne;
import demo.MyAnnotatedClass;
import demo.MyAnnotation;


public class ProcessTest {
	@Test
	public void main() {
		Operator[] checks = {
			new RequiresOne(
				new MethodAnnotated(MyAnnotation.class))
		};
		Processor p = new Processor(checks);
		assertTrue(
				p.process(MyAnnotatedClass.class));
	}
}
