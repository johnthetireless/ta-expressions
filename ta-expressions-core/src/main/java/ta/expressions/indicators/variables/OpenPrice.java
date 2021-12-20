package ta.expressions.indicators.variables;

import ta.expressions.core.Aggregate;

public class OpenPrice extends Variable {

	public static final String KEYWORD = "Open";
	
	public static final OpenPrice INSTANCE = new OpenPrice();
	
	private OpenPrice() {
		super(KEYWORD, Aggregate::open);
	}


}
