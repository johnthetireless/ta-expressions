package ta.expressions.indicators;

import ta.expressions.common.stats.SMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;

public class KAMA extends AnalyticFunction {

	public static final String KEYWORD = "KAMA";

	public static KAMA fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new KAMA(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;
	
	public KAMA(int n1, int n2, int n3) {
		super(functionRepresentation(KEYWORD, n1, n2, n3));

		NumericExpression close = ClosePrice.INSTANCE;
		NumericExpression er = new EfficiencyRatio(10);
		
		//actual arithmetic!!  doubles no less :)
		double fastRatio = 2.0 / (n2 + 1);
		double slowRatio = 2.0 / (n3 + 1);

		Constant slowSC = Constant.valueOf(slowRatio);
		Constant fastMinusSLow = Constant.valueOf(fastRatio - slowRatio);
		
		NumericExpression sc = er.multipliedBy(fastMinusSLow).plus(slowSC).squared();
		
		this.formula = new TernaryOperation(
				previous().isNull(), 
				new SMA(close, n1), 
				previous().plus(sc.multipliedBy(close.minus(previous())))
				);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
