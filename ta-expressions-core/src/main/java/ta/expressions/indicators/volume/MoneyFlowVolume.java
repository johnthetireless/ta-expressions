package ta.expressions.indicators.volume;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.Volume;

public class MoneyFlowVolume extends BasicAnalyticFunction {

	public static final String KEYWORD = "MoneyFlowVolume";
	
	private final static NumericExpression FORMULA 
				= CloseLocationValue.INSTANCE.multipliedBy(Volume.INSTANCE);
	
	public final static MoneyFlowVolume INSTANCE = new MoneyFlowVolume();

	private MoneyFlowVolume() {
		super(KEYWORD, FORMULA);
	}

}
