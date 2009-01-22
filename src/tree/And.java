package tree;

public class And implements Operator {
	public And(Operator ... ops) {
		for (Operator o : ops) {
			System.out.println(o);
		}
	}

	public boolean process(Class<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
}
