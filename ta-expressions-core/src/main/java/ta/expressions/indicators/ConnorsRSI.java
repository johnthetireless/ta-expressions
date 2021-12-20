package ta.expressions.indicators;

import ta.expressions.common.stats.Streak;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;

public class ConnorsRSI extends AnalyticFunction {

	public static final String KEYWORD = "ConnorsRSI";

	public static ConnorsRSI fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new ConnorsRSI(ps.intValue(0), ps.intValue(1), ps.intValue(2));
	}

	private final NumericExpression formula;
	
	public ConnorsRSI(int n1, int n2, int n3) {
		super(functionRepresentation(KEYWORD, n1, n2, n3));
		NumericExpression rsiPrice = new RSI(ClosePrice.INSTANCE, n1);
		NumericExpression rsiStreak = new RSI(new Streak(ClosePrice.INSTANCE), n2);
		NumericExpression percentRank = new PercentRank(ClosePrice.INSTANCE, n3);
		this.formula = rsiPrice.plus(rsiStreak).plus(percentRank).dividedBy(3);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
