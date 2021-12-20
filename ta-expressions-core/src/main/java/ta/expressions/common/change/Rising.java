package ta.expressions.common.change;

import ta.expressions.core.NumericExpression;

public class Rising extends RisingFalling {

	public static final String KEYWORD = "Rising";
	
	public Rising(NumericExpression e, int n, int missesAllowed) {
		super(KEYWORD, e, n, missesAllowed, (a,b) -> a.compareTo(b) >= 0);
	}

}
