package ta.expressions.indicators.macd;

import ta.expressions.common.stats.EMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.ParameterString;
import ta.expressions.indicators.variables.ClosePrice;

public class PPO extends AnalyticFunction {

	public static final String KEYWORD = "PPO";

	public static PPO fromString(String params) {
		ParameterString ps = new ParameterString(params);
		return new PPO(ps.intValue(0), ps.intValue(1));
	}

	private final NumericExpression formula;
	private final int n1;
	private final int n2;
	
	public PPO(int n1, int n2) {
		super(functionRepresentation(KEYWORD, n1, n2));
		NumericExpression ema1 = new EMA(ClosePrice.INSTANCE, n1); 
		NumericExpression ema2 = new EMA(ClosePrice.INSTANCE, n2); 
		this.formula = ema1.minus(ema2).dividedBy(ema2).multipliedBy(100);
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public AnalyticFunction signal(int n) {
		return new PPOSignal(this, n);
	}

	public AnalyticFunction histogram(int n) {
		return new PPOHistogram(n1, n2, n);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
	
	int n1() {
		return n1;
	}

	int n2() {
		return n2;
	}

}
