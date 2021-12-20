package ta.expressions.indicators.variables;

import ta.expressions.core.Aggregate;

public class LowPrice extends Variable {

	public static final String KEYWORD = "Low";
	
	public static final LowPrice INSTANCE = new LowPrice();
	
	private LowPrice() {
		super(KEYWORD, Aggregate::low);
	}

}
