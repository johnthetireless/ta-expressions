package ta.expressions.app;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

import ta.expressions.common.stats.SMA;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class FactoryTest {

	public static void main(String[] args) {

		String keyword = "ADX";
		
		NumericExpression e = null;
		
		for ( IntFunctionFactory f : IntFunctionFactory.values() ) {
			if ( f.keyword().equalsIgnoreCase(keyword) ) {
				e = f.of(14);
			}
		}
		
		System.out.println(e);

		Function<NumericExpression, IntFunction<NumericExpression>> sma1
			= new Function<NumericExpression, IntFunction<NumericExpression>>() {
				public IntFunction<NumericExpression> apply(NumericExpression e) {
					return new IntFunction<NumericExpression>() {
						public NumericExpression apply(int n) {
							return new SMA(e, n);
						}
					};
				}
		};

		NumericExpression e2 = sma1.apply(ClosePrice.INSTANCE).apply(20);
		System.out.println(e2);
		
		IntFunction<UnaryOperator<NumericExpression>> sma2 = new IntFunction<UnaryOperator<NumericExpression>>() {
			public UnaryOperator<NumericExpression> apply(int n) {
				return new UnaryOperator<NumericExpression>() {
					public NumericExpression apply(NumericExpression e) {
						return new SMA(e, n);
					}					
				};
			}
		};
		NumericExpression e3 = sma2.apply(30).apply(ClosePrice.INSTANCE);
		System.out.println(e3);
	}
	
}
