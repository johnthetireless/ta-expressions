package ta.expressions.app;

import java.util.function.IntFunction;

import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.adx.*;

public enum IntFunctionFactory {
	
	ADX_FUNCTION(ADX.KEYWORD, ADX::of);


	private IntFunctionFactory(String keyword, IntFunction<NumericExpression> intFunction) {
		this.keyword = keyword;
		this.factory = intFunction;
	}
	
	private String keyword;
	private IntFunction<NumericExpression> factory;
	
	public NumericExpression of(int n) {
		return factory.apply(n);
	}

	public String keyword() {
		return keyword;
	}

	
}
