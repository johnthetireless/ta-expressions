package ta.expressions.strategy;

public class WalkForwardSegment {	
	private final WalkForwardSlice inSample;
	private final WalkForwardSlice outOfSample;

	public WalkForwardSegment(int inSampleStart, int inSampleEnd, int outOfSampleEnd) {
		this.inSample = new WalkForwardSlice(inSampleStart, inSampleEnd);
		this.outOfSample = new WalkForwardSlice(inSampleEnd + 1, outOfSampleEnd);
	}

	public WalkForwardSlice inSample() {
		return inSample;
	}

	public WalkForwardSlice outOfSample() {
		return outOfSample;
	}

	@Override
	public String toString() {
		return "WalkForwardSegment [inSample=" + inSample + ", outOfSample=" + outOfSample + "]";
	}
	
}
