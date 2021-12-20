package ta.expressions.indicators.variables;

import ta.expressions.core.Aggregate;

public class HighPrice extends Variable {

	public static final String KEYWORD = "High";
	
	public static final HighPrice INSTANCE = new HighPrice();
	
	private HighPrice() {
		super(KEYWORD, Aggregate::high);
	}

}
