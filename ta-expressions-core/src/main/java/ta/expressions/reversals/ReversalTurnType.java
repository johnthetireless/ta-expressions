package ta.expressions.reversals;

public enum ReversalTurnType {

	UPTURN("Upturn"),
	DOWNTURN("Downturn");
	
	private ReversalTurnType(String keyword) {
		this.keyword = keyword;
	}

	private String keyword;

	public String keyword() {
		return keyword;
	}


}
