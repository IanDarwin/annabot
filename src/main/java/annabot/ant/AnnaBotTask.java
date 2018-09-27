package annabot.ant;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.resources.FileResource;

import annabot.AnnaBot;
import annabot.Claim;

import com.darwinsys.io.ClassSourceUtils;

public class AnnaBotTask extends Task {

	private FileSet claims;
	private FileSet targets;
	private Path inputClassPath;

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
	
	public void addConfiguredClassPath(Path p) {
		this.inputClassPath = p;
		System.out.println(inputClassPath);
	}
	
	@Override
	public void execute() throws BuildException {
		System.out.println("CLASSPATH");
		List<URL> classpath = new ArrayList<URL>();
		if (inputClassPath != null) {
			Iterator<FileResource> it = inputClassPath.iterator();
			while (it.hasNext()) {
				FileResource o = it.next();
				System.out.println(o + "--" + o.getClass());
				try {
					URL fileURL = ClassSourceUtils.makeFileURL(o.getFile().getAbsolutePath());
					classpath.add(fileURL);
				} catch (IOException e) {
					throw new BuildException("getting url for classpath: " + e, e);
				}
			}
		}
		System.out.println("CLAIMS:");
		List<Claim> claimClasses = new ArrayList<Claim>();
		URL claimsURL = null;
		try {
			claimsURL = ClassSourceUtils.makeFileURL(claims.getDir().getAbsolutePath());
		} catch (IOException e1) {
			throw new BuildException("Make Class loader URL failed + e", e1);
		}
		List<URL> claimsURLs = new ArrayList<URL>(classpath);
		claimsURLs.add(claimsURL);
		ClassLoader cl = new URLClassLoader(claimsURLs.toArray(new URL[claimsURLs.size()]));
		final Iterator<FileResource> claimList = claims.iterator();
		while (claimList.hasNext()) {
			FileResource o = claimList.next();
			File file = o.getFile();
			try {
				claimClasses.add((Claim)(ClassSourceUtils.doFile(file, cl).newInstance()));			
				System.out.println(file);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		
		System.out.println("TARGETS");
		URL urlt = null;
		try {
			urlt = ClassSourceUtils.makeFileURL(claims.getDir().getAbsolutePath());
		} catch (IOException e1) {
			throw new BuildException("Make Class loader failed + e", e1);
		}
		List<URL> targetsURLs = new ArrayList<URL>(classpath);
		targetsURLs.add(urlt);
		ClassLoader clt = new URLClassLoader(targetsURLs.toArray(new URL[claimsURLs.size()]));
		List<Class<?>> targetClasses = new ArrayList<Class<?>>(); // ClassSourceUtils.classListFromSource(classesToTest, classPathElements);
		final Iterator<FileResource> targetsList = targets.iterator();
		while (targetsList.hasNext()) {
			FileResource o = targetsList.next();
			File file = o.getFile();
			targetClasses.add(ClassSourceUtils.doFile(file, clt));
			System.out.println(file);
		}
		
		// The real work
		try {
			AnnaBot.process(targetClasses, claimClasses);
		} catch (Exception e) {
			throw new BuildException("AnnaBot threw exception: e", e);
		}
	}
}
