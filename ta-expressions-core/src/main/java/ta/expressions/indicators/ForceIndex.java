package ta.expressions.indicators;

import ta.expressions.common.change.Change;
import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.Volume;

public class ForceIndex extends AnalyticFunction {

	public static final String KEYWORD = "ForceIndex";

	public static ForceIndex fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ForceIndex(ps.intValue(0));
	}
	
	private final NumericExpression formula;

	public ForceIndex(int n) {
		super(functionRepresentation(KEYWORD, n));
		NumericExpression force = new Change(ClosePrice.INSTANCE).multipliedBy(Volume.INSTANCE);
		this.formula = new EMA(force, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
