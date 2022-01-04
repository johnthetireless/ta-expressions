package ta.expressions.patterns;

import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;
import ta.expressions.indicators.variables.OpenPrice;

public abstract class Reversal extends BooleanExpression {

	private final HighLowTrend trend;
	
	public Reversal(String representation, TrendType trendType, int minTrendPeriods) {
		super(representation);
		this.trend = HighLowTrend.previous(trendType, minTrendPeriods, 1);
	}
	
	public abstract BooleanExpression formula();
	
	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		return formula().evaluate(context, index);
	}

	@Override
	public Set<NumericExpression> terms() {
		return formula().terms();
	}

	protected HighLowTrend trend() {
		return trend;
	}
	
	protected BooleanExpression openNearHigh(double nearThreshold) {
		return new PriceNearPrice(OpenPrice.INSTANCE, HighPrice.INSTANCE, nearThreshold);
	}

	protected BooleanExpression openNearLow(double nearThreshold) {
		return new PriceNearPrice(OpenPrice.INSTANCE, LowPrice.INSTANCE, nearThreshold);
	}

	protected BooleanExpression closeNearHigh(double nearThreshold) {
		return new PriceNearPrice(ClosePrice.INSTANCE, HighPrice.INSTANCE, nearThreshold);
	}

	protected BooleanExpression closeNearLow(double nearThreshold) {
		return new PriceNearPrice(ClosePrice.INSTANCE, LowPrice.INSTANCE, nearThreshold);
	}
	


}
