package ta.expressions.demo;


import ta.expressions.core.AnalyticFunction;
import ta.expressions.indicators.MedianPrice;
import ta.expressions.indicators.PriceRange;
import ta.expressions.indicators.TypicalPrice;
import ta.expressions.indicators.volume.CloseLocationValue;
import ta.expressions.indicators.volume.MoneyFlowVolume;
import ta.expressions.indicators.adx.PlusDM;
import ta.expressions.indicators.volatility.TrueHigh;
import ta.expressions.indicators.volatility.TrueLow;
import ta.expressions.indicators.volatility.TrueRange;
import ta.expressions.indicators.adx.MinusDM;

public enum TechnicalAnalysisTerm {

	MEDIAN_PRICE(MedianPrice.KEYWORD, MedianPrice.INSTANCE),
	PRICE_RANCE(PriceRange.KEYWORD, PriceRange.INSTANCE),
	TYPICAL_PRICE(TypicalPrice.KEYWORD, TypicalPrice.INSTANCE),
	CLOSE_LOCATION_VALUE(CloseLocationValue.KEYWORD, CloseLocationValue.INSTANCE),
	MONEY_FLOW_VOLUME(MoneyFlowVolume.KEYWORD, MoneyFlowVolume.INSTANCE),
	TRUE_HIGH(TrueHigh.KEYWORD, TrueHigh.INSTANCE),
	TRUE_LOW(TrueLow.KEYWORD, TrueLow.INSTANCE),
	TRUE_RANGE(TrueRange.KEYWORD, TrueRange.INSTANCE),
	PLUS_DM(PlusDM.KEYWORD, PlusDM.INSTANCE),
	MINUS_DM(MinusDM.KEYWORD, MinusDM.INSTANCE);
	
	private TechnicalAnalysisTerm(String keyword, AnalyticFunction expression) {
		this.keyword = keyword;
		this.expression = expression;
	}
	
	private String keyword;
	private AnalyticFunction expression;
	
	public String keyword() {
		return keyword;
	}
	
	public AnalyticFunction expression() {
		return expression;
	}
	
	public String equation() {
		return expression.equation();
	}
	

}
