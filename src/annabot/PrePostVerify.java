package annabot;

/**
 * Claims implement PrePostVerify to be notified before and after
 * the verification phase; the canonical example is setting a counter
 * to zero in preVerify and examining it in postVerify.
 */
public interface PrePostVerify {
	void preVerify();
	void postVerify();
}
