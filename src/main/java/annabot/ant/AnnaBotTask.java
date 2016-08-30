package annabot.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Java;

/** 
 * Quick-n-dirty Ant task that reuses the standard
 * Ant Java Task to call the AnnaBot main program.
 * Maybe simple is good?
 * @author Ian Darwin
 */
public class AnnaBotTask extends Java {
	private String targets;
	private String claims;

	public void setClaims(String c) {
		if (claims != null) {
			throw new BuildException("only one claims allowed");
		}
		this.claims = c;
	}

	public void setTargets(String t) {
		if (targets != null) {
			throw new BuildException("only one targets allowed");
		}
		this.targets = t;
	}
	
	@Override
	public void execute() throws BuildException {
		if (claims == null) {
			throw new BuildException("'claims' is a required attribute");
		}
		if (targets == null) {
			throw new BuildException("'targets' is a required attribute");
		}
		setArgs(claims + " " + targets);
		setClassname("annabot.AnnaBot");
		super.execute();
	}
}
