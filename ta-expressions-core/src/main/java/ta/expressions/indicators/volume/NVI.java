package ta.expressions.indicators.volume;

import ta.expressions.common.change.Change;
import ta.expressions.common.change.RateOfChange;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.Volume;

public class NVI extends AnalyticFunction {

	public static final String KEYWORD = "NVI";

	public static final NVI INSTANCE = new NVI();
	
	public static NVI fromString(String ignored) {
		return INSTANCE;
	}
	
	private NVI() {
		super(KEYWORD);
	}


	@Override
	protected NumericExpression getFormula() {
		
		return new TernaryOperation(
				previous().isNull(),
				Constant.valueOf(1000),
				new TernaryOperation(
						new Change(Volume.INSTANCE, 1).lessThan(0),
						previous().plus(new RateOfChange(ClosePrice.INSTANCE, 1).multipliedBy(100)),
						previous()
						)
				);
	}
	
}
