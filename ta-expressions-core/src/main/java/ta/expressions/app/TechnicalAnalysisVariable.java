package ta.expressions.app;

import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.HighPrice;
import ta.expressions.indicators.variables.LowPrice;
import ta.expressions.indicators.variables.OpenPrice;
import ta.expressions.indicators.variables.Volume;

public enum TechnicalAnalysisVariable {
	
	OPEN_PRICE(OpenPrice.KEYWORD, OpenPrice.INSTANCE),
	HIGH_PRICE(HighPrice.KEYWORD, HighPrice.INSTANCE),
	LOW_PRICE(LowPrice.KEYWORD, LowPrice.INSTANCE),
	CLOSE_PRICE(ClosePrice.KEYWORD, ClosePrice.INSTANCE),
	VOLUME(Volume.KEYWORD, Volume.INSTANCE);
	
	
	private TechnicalAnalysisVariable(String keyword, NumericExpression expression) {
		this.keyword = keyword;
		this.expression = expression;
	}
	
	private String keyword;
	private NumericExpression expression;
	
	public String keyword() {
		return keyword;
	}
	
	public NumericExpression expression() {
		return expression;
	}
	
}
