package ta.expressions.indicators;

import ta.expressions.common.change.Gain;
import ta.expressions.common.change.Loss;
import ta.expressions.common.stats.Summation;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

/**
 * This is similar to the RSI.
 * It uses summations rather than MMAs, and it checks gain/loss over a specified number of periods.
 * RSI always checks gain and loss for immediately previous period.
 * All of these RSI-like classes might be combined.
 * The source of this formula https://www.marketvolume.com/technicalanalysis/relativemomentumindex.asp
 * has a few suspect formulations.
 *
 */
public class RelativeMomentumIndex extends AnalyticFunction {

	public static final String KEYWORD = "RMI";

	private final NumericExpression formula;
	
	public RelativeMomentumIndex(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		NumericExpression sumOfGains = new Summation(new Gain(ClosePrice.INSTANCE, n2), n1);
		NumericExpression sumOfLosses = new Summation(new Loss(ClosePrice.INSTANCE, n2), n1);
		this.formula = sumOfGains.multipliedBy(100).divideOrZero(sumOfGains.plus(sumOfLosses));
	}
	
	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
