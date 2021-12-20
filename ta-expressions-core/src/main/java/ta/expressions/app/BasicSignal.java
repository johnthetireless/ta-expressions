package ta.expressions.app;

import java.math.BigDecimal;
import java.util.Map;

import ta.expressions.core.Aggregate;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.signal.Signal;

public class BasicSignal implements Signal {
	
	private final int index;
	private final BooleanExpression cause;
	private final Map<NumericExpression, BigDecimal> values;
	private final Aggregate input;

	
	
	public BasicSignal(int index, BooleanExpression cause, Map<NumericExpression, BigDecimal> values, Aggregate input) {
		super();
		this.index = index;
		this.cause = cause;
		this.values = values;
		this.input = input;
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public BooleanExpression cause() {
		return cause;
	}

	@Override
	public Map<NumericExpression, BigDecimal> values() {
		return values;
	}

	@Override
	public Aggregate input() {
		return input;
	}

	@Override
	public String toString() {
		return "BasicSignal [index=" + index + ", cause=" + cause + ", values=" + values + ", input=" + input + "]";
	}

	
	
}
