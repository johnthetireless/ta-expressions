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
		this.formula = new TernaryOperation(
				sum.equalTo(0), 
				Constant.valueOf(0),
				diff.abs().dividedBy(sum).multipliedBy(100)
				);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}


}
