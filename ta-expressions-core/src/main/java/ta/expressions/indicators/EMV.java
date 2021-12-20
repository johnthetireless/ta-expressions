package ta.expressions.indicators;

import ta.expressions.common.change.Change;
import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.Volume;

public class EMV extends AnalyticFunction {

	public static final String KEYWORD = "EMV";

	public static EMV fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new EMV(ps.intValue(0));
	}
	
	private final NumericExpression formula;

	public EMV(int n) {
		super(functionRepresentation(KEYWORD, n));
		
		NumericExpression dm = new Change(MedianPrice.INSTANCE);
		NumericExpression br = Volume.INSTANCE.dividedBy(100000000).dividedBy(PriceRange.INSTANCE);
		NumericExpression emv1 = dm.dividedBy(br);
		this.formula = n == 1 ? emv1 : new SMA(emv1, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
