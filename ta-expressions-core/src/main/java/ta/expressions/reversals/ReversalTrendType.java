package ta.expressions.reversals;

public enum ReversalTrendType {

	UPTREND("Uptrend"),
	DOWNTREND("Downtrend");
	
	private ReversalTrendType(String keyword) {
		this.keyword = keyword;
	}

	private String keyword;
	
	public String keyword() {
		return keyword;
	}

}
