package ta.expressions.demo;

import java.util.List;

import ta.expressions.common.stats.CorrelationCoefficient;
import ta.expressions.common.stats.Covariance;
import ta.expressions.common.stats.Deviation;
import ta.expressions.common.stats.EMA;
import ta.expressions.common.stats.HighestValue;
import ta.expressions.common.stats.LowestValue;
import ta.expressions.common.stats.MMA;
import ta.expressions.common.stats.SMA;
import ta.expressions.common.stats.Slope;
import ta.expressions.common.stats.StandardDeviation;
import ta.expressions.common.stats.Streak;
import ta.expressions.common.stats.Summation;
import ta.expressions.common.stats.Variance;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.RSI;
import ta.expressions.indicators.variables.ClosePrice;

/**
 * Statistics functions and formulas
 *
 */
public class Stats {

	public static void main(String[] args) {
		
		NumericExpression close = ClosePrice.INSTANCE;
		NumericExpression rsi = new RSI(14);
		
		List<NumericExpression> expressions = List.of(
				new Summation(close, 10),
				new HighestValue(close, 10),
				new LowestValue(close, 10),
				new Slope(close, 10),
				new Streak(close)
				);
		
		System.out.println("Built-in statistical functions...........");
		for ( NumericExpression e : expressions ) {
			System.out.println(e);
		}
		System.out.println();
		
		List<AnalyticFunction> functions = List.of(
				new SMA(close, 10),
				new EMA(close, 10),
				new MMA(close, 10),
				new Deviation(close, 10),
				new Variance(close, 10),
				new StandardDeviation(close, 10),
				new Covariance(close, rsi, 10),
				new CorrelationCoefficient(close, rsi, 10)
				);
		
		System.out.println("Derived statistical functions..........");
		for ( AnalyticFunction f : functions ) {
			System.out.println(f.equation());
		}
		System.out.println();
		/*
		System.out.println("RSI equations.........");		
		System.out.println(new RSI(14).equation());
		System.out.println(new RelativeStrength(14).equation());
		System.out.println(new Gain(close, 1).equation());
		System.out.println(new Loss(close, 1).equation());
		System.out.println(new Change(close, 1).equation());
		System.out.println();
		
		System.out.println("All CC subexpressions.........");	
		NumericExpression cc = new CorrelationCoefficient(close, rsi, 10);
		for ( NumericExpression e : cc.subexpressions() ) {
			System.out.println(e);
		}
		System.out.println(cc.subexpressions().size());
		System.out.println();
		
		List<AnalyticFunction> fs = new ArrayList<>();
		for ( NumericExpression e : cc.subexpressions() ) {
			if ( e instanceof AnalyticFunction ) {
				fs.add((AnalyticFunction) e);
			}
		}
		
		System.out.println("CC sub-functions.........");	
		for ( AnalyticFunction f : fs ) {
			System.out.println(f.equation());
		}
		System.out.println(fs.size());
		System.out.println();
		*/
	}

}
