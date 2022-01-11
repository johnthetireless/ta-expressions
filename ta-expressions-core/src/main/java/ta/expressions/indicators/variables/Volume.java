package ta.expressions.indicators.variables;

import ta.expressions.core.Aggregate;

public class Volume extends Variable {

	public static final String KEYWORD = "Volume";

	public static final Volume INSTANCE = new Volume();
	
	private Volume() {
		super(KEYWORD, Aggregate::volume);
	}


}
