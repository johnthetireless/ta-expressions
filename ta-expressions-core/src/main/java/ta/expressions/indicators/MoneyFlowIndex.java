package ta.expressions.indicators;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.Volume;

public class MoneyFlowIndex extends AnalyticFunction {

	public static final String KEYWORD = "MFI";

	public static MoneyFlowIndex fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new MoneyFlowIndex(ps.intValue(0));
	}
	
	private final NumericExpression formula;

	public MoneyFlowIndex(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression rawMoneyFlow = TypicalPrice.INSTANCE.multipliedBy(Volume.INSTANCE);
		this.formula = new RSI(rawMoneyFlow, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
}
