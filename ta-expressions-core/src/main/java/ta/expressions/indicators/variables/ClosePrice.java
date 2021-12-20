package ta.expressions.indicators.variables;

import ta.expressions.core.Aggregate;

public class ClosePrice extends Variable {

	public static final String KEYWORD = "Close";
	
	public static final ClosePrice INSTANCE = new ClosePrice();
	
	private ClosePrice() {
		super(KEYWORD, Aggregate::close);
	}

}
