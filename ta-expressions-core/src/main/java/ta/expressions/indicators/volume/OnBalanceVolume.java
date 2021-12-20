package ta.expressions.indicators.volume;

import ta.expressions.common.change.Change;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.Volume;

public class OnBalanceVolume extends AnalyticFunction {
	
	public static final String KEYWORD = "OBV";
	
	public final static OnBalanceVolume INSTANCE = new OnBalanceVolume();

	public static OnBalanceVolume fromString(String ignored) {
		return INSTANCE;
	}
	
	private final NumericExpression formula;
	
	private OnBalanceVolume() {
		super(KEYWORD);
		NumericExpression priceChange = new Change(ClosePrice.INSTANCE, 1);
		NumericExpression signum = priceChange.signum();
		this.formula = new TernaryOperation(
				previous().isNull(), 
				Constant.valueOf(0), 
				previous().plus(Volume.INSTANCE.multipliedBy(signum))
				);
	}
	
	

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}



////	@Override
////	public BigDecimal evaluate(AnalysisContext context, int index) {
////		
////		return formula.evaluate(context, index);
////		
////	}
////
//	@Override
//	public Set<NumericExpression> operands() {
//		return Set.of();
//	}
	
	

}
