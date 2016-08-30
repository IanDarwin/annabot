package annabot;

/**
 * Reporter is used to report when a claim
 * that should apply to a class does not.
 */
public interface Reporter {
	void report(Class<?> target, Claim claim);
}
