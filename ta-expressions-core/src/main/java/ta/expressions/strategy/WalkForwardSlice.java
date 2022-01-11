package ta.expressions.strategy;

/**
 * Start and end indices used to define a range of inputs from a data set,
 * used for "in sample" optimization or "out of sample" back testing.
 *
 */
public class WalkForwardSlice {
	private final int start;
	private final int end;
	
	public WalkForwardSlice(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int start() {
		return start;
	}

	public int end() {
		return end;
	}

	@Override
	public String toString() {
		return "WalkForwardSlice [start=" + start + ", end=" + end + "]";
	}
	
}
