package ta.expressions.indicators.volume;

import ta.expressions.core.BasicAnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.PriceRange;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;

public class CloseLocationValue extends BasicAnalyticFunction {
		
	public static final String KEYWORD = "CloseLocationValue";
	
	private final static NumericExpression FORMULA = new TernaryOperation(
			PriceRange.INSTANCE.equalTo(0),
			Constant.valueOf(0),
			ClosePrice.INSTANCE.minus(LowPrice.INSTANCE)
				.minus(HighPrice.INSTANCE.minus(ClosePrice.INSTANCE))
				.dividedBy(PriceRange.INSTANCE)
			);

	public static final CloseLocationValue INSTANCE = new CloseLocationValue();
	
	private CloseLocationValue() {
		super(KEYWORD, FORMULA);
	}


}
