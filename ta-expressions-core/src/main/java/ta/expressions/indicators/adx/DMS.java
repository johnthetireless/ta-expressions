package ta.expressions.indicators.adx;

import java.util.List;

import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.NumericExpression;

public class DMS {

	private final AnalyticFunction adx;
	private final AnalyticFunction plusDI;
	private final AnalyticFunction minusDI;
	
	public DMS(int n) {
		this.adx = new ADX(n);
		this.plusDI = new PlusDI(n);
		this.minusDI = new MinusDI(n);
	}

	public AnalyticFunction adx() {
		return adx;
	}

	public AnalyticFunction plusDI() {
		return plusDI;
	}

	public AnalyticFunction minusDI() {
		return minusDI;
	}

	public List<NumericExpression> expressions() {
		return List.of(adx, plusDI, minusDI);
	}

}
