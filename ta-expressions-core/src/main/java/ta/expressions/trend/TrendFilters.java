package ta.expressions.trend;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.macd.MACD;
import ta.expressions.indicators.stochastics.StochK;

public class TrendFilters {
	
	public static BooleanExpression macdStochLongBuyFilter(int macdShort, int macdLong, int macdSignal, int stochPeriods) {
		NumericExpression macd = new MACD(macdShort, macdLong);
		NumericExpression signal = new EMA(macd, macdSignal);
		NumericExpression sk = new StochK(stochPeriods);
		NumericExpression sd = new EMA(sk, 3);
		return macd.greaterThan(signal).and(sk.greaterThan(sd));
	}
	
	public static BooleanExpression macdStochLongExitFilter(int macdShort, int macdLong, int macdSignal, int stochPeriods) {
		NumericExpression macd = new MACD(macdShort, macdLong);
		NumericExpression signal = new EMA(macd, macdSignal);
		NumericExpression sk = new StochK(stochPeriods);
		NumericExpression sd = new EMA(sk, 3);
		return macd.lessThan(signal).and(sk.lessThan(sd));
	}
	
	public static void main(String[] args) {
		BooleanExpression buy = macdStochLongBuyFilter(10, 30, 10, 20);
		System.out.println(buy);
		BooleanExpression sell = macdStochLongExitFilter(10, 30, 10, 20);
		System.out.println(sell);
	}
	
	
}
