package ta.expressions.indicators.aroon;

import java.util.List;

import ta.expressions.core.NumericExpression;

public class Aroon {
	
	private final NumericExpression up;
	private final NumericExpression down;
	private final NumericExpression oscillator;
	
	public Aroon(int n) {
		this.up = new AroonUp(n);
		this.down = new AroonDown(n);
		this.oscillator = new AroonOscillator(n);
	}

	public NumericExpression up() {
		return up;
	}
	
	public NumericExpression down() {
		return down;
	}
	
	public NumericExpression oscillator() {
		return oscillator;
	}
	
	public List<NumericExpression> expressions() {
		return List.of(up, down);
	}
	
}
