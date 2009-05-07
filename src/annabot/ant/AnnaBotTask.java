package annabot.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;

public class AnnaBotTask extends Task {

	private Path claims;

	@Override
	public void init() throws BuildException {
		super.init();
	}
	
	public void addPath(Path claims) {
		this.claims = claims;
	}
	
	@Override
	public void execute() throws BuildException {
		System.out.println(claims);
		super.execute();
	}
}
