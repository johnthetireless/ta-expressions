package ta.expressions.indicators;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class HMA extends AnalyticFunction {

	public static final String KEYWORD = "HMA";

	private final NumericExpression formula;
	
	public HMA(NumericExpression e, int n) {
		super(functionRepresentation(KEYWORD, n));
		WMA wma1 = new WMA(e, n / 2);
		WMA wma2 = new WMA(e, n);
		NumericExpression raw = wma1.multipliedBy(2).minus(wma2);
		int sqrtN = (int)Math.sqrt(n);
		this.formula = new WMA(raw, sqrtN);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
