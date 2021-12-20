package ta.expressions.indicators.volatility;

import ta.expressions.core.BasicAnalyticFunction;

public class TrueRange extends BasicAnalyticFunction {
	
	public static final String KEYWORD = "TrueRange";

	public static final TrueRange INSTANCE = new TrueRange();

	private TrueRange() {
		super(KEYWORD, TrueHigh.INSTANCE.minus(TrueLow.INSTANCE));
	}
}
