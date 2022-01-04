package ta.expressions.indicators.adx;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;

public class DX extends AnalyticFunction {

	private final NumericExpression formula;
	
	public DX(int n) {
		super(functionRepresentation("DX", n));
		NumericExpression plusDI = new PlusDI(n);
		NumericExpression minusDI = new MinusDI(n);
		NumericExpression sum = plusDI.plus(minusDI);
		NumericExpression diff = plusDI.minus(minusDI);
		NumericExpression dividend = diff.abs().multipliedBy(100);
		this.formula = dividend.divideOrZero(sum);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
