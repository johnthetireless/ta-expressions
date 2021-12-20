package ta.expressions.indicators.bollinger;

import java.util.List;

import ta.expressions.common.stats.MovingAverage;
import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class BollingerBands {
	
	private final MovingAverage ma;
	private final Number k;
	
	private final AnalyticFunction middle;
	private final AnalyticFunction upper;
	private final AnalyticFunction lower;

	public BollingerBands(int n, Number k) {
		this(new SMA(ClosePrice.INSTANCE, n), n, k);
	}
	
	public BollingerBands(MovingAverage ma, int n, Number k) {
		this.ma = ma;
		this.k = k;
		this.middle = new BollingerMiddleBand(ma);
		this.upper = new BollingerUpperBand(middle, n, k);
		this.lower = new BollingerLowerBand(middle, n, k);
	}

	public AnalyticFunction middle() {
		return middle;
	}
	
	public AnalyticFunction upper() {
		return upper;
	}
	
	public AnalyticFunction lower() {
		return lower;
	}
	
	public AnalyticFunction bandwidth() {
		return new BollingerBandwidth(upper, middle, lower, ma.periods(), k);
	}
	
	public AnalyticFunction percentB() {
		return new BollingerPercentB(ma.operand(), upper, lower, ma.periods(), k);
	}
	
	public List<NumericExpression> expressions() {
		return List.of(upper, middle, lower);
	}
	
}
