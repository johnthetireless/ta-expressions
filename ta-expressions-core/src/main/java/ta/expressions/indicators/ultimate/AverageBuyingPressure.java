package ta.expressions.indicators.ultimate;

import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.volatility.TrueRange;

public class AverageBuyingPressure extends AnalyticFunction {

	private final NumericExpression formula;
	
	public AverageBuyingPressure(int n) {
		super(functionRepresentation("AverageBP", n));
		this.formula = new Summation(BuyingPressure.INSTANCE, n)
				.dividedBy(new Summation(TrueRange.INSTANCE, n));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
