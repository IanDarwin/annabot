package annabot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static tree.OperatorUtils.FALSE;

import org.junit.Test;

import tree.MethodAnnotated;
import tree.Operator;
import tree.RequiresOne;
import demo.MyAnnotatedClass;
import demo.MyAnnotation;


public class ProcessTest {
	Claim checks = new Claim() {
		@Override
		public String getDescription() {
			return "Fake Claim w/ RequiresOne(MethodAnnotated(MyAnnotation))";
		}
		@Override
		public Operator[] getClassFilter() {
			return null;
		}
		@Override
		public Operator[] getOperators() {
			return new Operator[]{
				new RequiresOne(
					new MethodAnnotated(MyAnnotation.class))
			};
		}		
	};

	Claim failingChecks = new Claim() {
		@Override
		public String getDescription() {
			return "Fake Claim w/ RequiresOne(MethodAnnotated(MyAnnotation))";
		}
		@Override
		public Operator[] getClassFilter() {
			return null;
		}
		@Override
		public Operator[] getOperators() {
			return new Operator[]{
				new RequiresOne(
					new MethodAnnotated(MyAnnotation.class))
			};
		}		
	};
	
	Claim noneShallPassFilter = new Claim() {
		@Override
		public String getDescription() {
			return "Fake Claim with getClassFilter returning FALSE";
		}
		@Override
		public Operator[] getClassFilter() {
			return new Operator[]{FALSE};
		}
		@Override
		public Operator[] getOperators() {
			fail("Should not have called getOperators");
			return null;
		}
		
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
	
	@Test
	public void testNegativeFilter() {
		Processor p = new Processor(noneShallPassFilter);
		p.process(Object.class);
	}
}
