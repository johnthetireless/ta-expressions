package ta.expressions.indicators.keltner;

import java.math.BigDecimal;
import java.util.List;

import ta.expressions.common.stats.EMA;
import ta.expressions.common.stats.MovingAverage;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Expression;
import ta.expressions.indicators.variables.ClosePrice;

public class KeltnerChannels {

	private final AnalyticFunction middle;
	private final AnalyticFunction upper;
	private final AnalyticFunction lower;

	public KeltnerChannels(int n, Number k) {
		this(n, n/2, k);
	}

	public KeltnerChannels(int nEMA, int nATR, Number k) {
		this(new EMA(ClosePrice.INSTANCE, nEMA), nATR, k);
	}

	public KeltnerChannels(MovingAverage ma, int nATR, Number k) {
		this.middle = new KeltnerMiddleChannel(ma);
		this.upper = new KeltnerUpperChannel(ma, nATR, k);
		this.lower = new KeltnerLowerChannel(ma, nATR, k);
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
	
	public List<Expression<BigDecimal>> expressions() {
		return List.of(upper, middle, lower);
	}
	

}
