package ta.expressions.app;

import java.util.function.Function;

import ta.expressions.core.NumericExpression;

public class TermParser {

	public NumericExpression apply(String s) {
		
		for ( TechnicalAnalysisIndicator i : TechnicalAnalysisIndicator.values() ) {
			String keyword = i.keyword();
			
			if ( s.toUpperCase().startsWith(keyword.toUpperCase()) ) {
				s = s.substring(keyword.length() + 1, s.length() - 1);
				Function<String, NumericExpression> factory = i.factory();
				return factory.apply(s);
			}	
		}
		return null;
	}

}
