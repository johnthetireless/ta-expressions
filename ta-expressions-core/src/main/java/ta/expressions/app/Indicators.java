package ta.expressions.app;

import ta.expressions.common.change.Change;
import ta.expressions.common.change.RateOfChange;
import ta.expressions.common.stats.EMA;
import ta.expressions.common.stats.HighestValue;
import ta.expressions.common.stats.LowestValue;
import ta.expressions.common.stats.SMA;
import ta.expressions.common.stats.StandardDeviation;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.volatility.ChoppinessIndex;

public class Indicators {

	public static void main(String[] args) {
		System.out.println();
		for ( TechnicalAnalysisIndicator ind : TechnicalAnalysisIndicator.values() ) {
			
			String keyword = ind.keyword();
			String desc = ind.description();
			System.out.println(desc + " ---------- Keyword: " + keyword );
		}
		
		System.out.println();
		System.out.println();

		System.out.println("Total: " + TechnicalAnalysisIndicator.values().length);

		NumericExpression close = ClosePrice.INSTANCE;
		System.out.println(close.previous());
		
		System.out.println(new Change(close, 10));
		System.out.println(new RateOfChange(close, 5));
		
		System.out.println(new HighestValue(close, 25));
		System.out.println(new LowestValue(close, 25));
		
		System.out.println(new SMA(close, 50));
		System.out.println(new EMA(close, 50));
		System.out.println(new StandardDeviation(close, 50));

		System.out.println(new ChoppinessIndex(14).equation());
	}

}
