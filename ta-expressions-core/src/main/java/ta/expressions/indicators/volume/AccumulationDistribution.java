package ta.expressions.indicators.volume;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;

public class AccumulationDistribution extends AnalyticFunction {
	
	public static final String KEYWORD = "AccumDist";

	public static final AccumulationDistribution INSTANCE = new AccumulationDistribution();
	
	public static AccumulationDistribution fromString(String ignored) {
		return INSTANCE;
	}
	
	private AccumulationDistribution() {
		super(KEYWORD);
	}


	@Override
	protected NumericExpression getFormula() {
		
		return new TernaryOperation(
				previous().isNull(),
				MoneyFlowVolume.INSTANCE,
				previous().plus(MoneyFlowVolume.INSTANCE)
				);
	}
	
	
	
}
