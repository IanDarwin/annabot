package annabot.ant;

import java.util.Iterator;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;

public class AnnaBotTask extends Task {

	private Path claims;

	@Override
	public void init() throws BuildException {
		super.init();
	}
	
	public void setClaims(Path claims) {
		this.claims = claims;
	}
	
	@Override
	public void execute() throws BuildException {
		final Iterator claimList = claims.iterator();
		while (claimList.hasNext()) {
			Object o = claimList.next();
			System.out.println(o.getClass());
			System.out.println(o);
		}
		super.execute();
	}
}
