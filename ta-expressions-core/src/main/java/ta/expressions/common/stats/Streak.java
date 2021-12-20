package ta.expressions.common.stats;

import java.math.BigDecimal;
import java.util.Set;

import ta.expressions.common.change.Change;
import ta.expressions.core.AnalysisContext;
import ta.expressions.core.NumericExpression;

//TODO: subclass of ArithmeticExpression?
public class Streak extends NumericExpression {

	private final NumericExpression change;

	public Streak(NumericExpression e) {
		super(functionRepresentation("Streak", e));
		this.change = new Change(e);
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		
		BigDecimal previousStreak = getValue(context, index - 1);
		BigDecimal changeValue = change.getValue(context, index); 
		if ( previousStreak == null || changeValue == null) { //sb the same thing
			return BigDecimal.ZERO;
		}
		
		int streakSignum = previousStreak.signum();
		int changeSignum = changeValue.signum();
		int streakInt = previousStreak.intValue();
		
		if ( changeSignum > 0 ) {
				return streakSignum >= 0 ? BigDecimal.valueOf(streakInt + 1) : BigDecimal.ZERO;
		}
		else if ( changeSignum < 0) {
			return streakSignum <= 0 ? BigDecimal.valueOf(streakInt - 1) : BigDecimal.ZERO;			
		}
		else { //no change; streak is broken
			return BigDecimal.ZERO;			
		}
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of(change);
	}
	
	
}
