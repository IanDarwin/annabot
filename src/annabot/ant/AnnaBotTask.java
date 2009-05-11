package annabot.ant;

import java.util.Iterator;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.resources.FileResource;

public class AnnaBotTask extends Task {

	private FileSet claims;
	private FileSet targets;

	@Override
	public void init() throws BuildException {
		super.init();
	}
	
	public void addConfiguredClaims(FileSet claims) {
		this.claims = claims;
	}
	
	public void addConfiguredTargets(FileSet targets) {
		this.targets = targets;
	}
	
	@Override
	public void execute() throws BuildException {
		System.out.println("CLAIMS:");
		final Iterator<FileResource> claimList = claims.iterator();
		while (claimList.hasNext()) {
			FileResource o = claimList.next();
			System.out.println(o);
		}
		
		System.out.println("TARGETS");
		final Iterator<FileResource> targetsList = targets.iterator();
		while (targetsList.hasNext()) {
			FileResource o = targetsList.next();
			System.out.println(o);
		}
		super.execute();
	}
}
