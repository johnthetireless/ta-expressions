package ta.expressions.indicators;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;

import ta.expressions.common.change.Change;
import ta.expressions.core.AnalysisContext;
import ta.expressions.core.NumericExpression;

//TODO: subclass of ArithmeticExpression?
public class PercentRank extends NumericExpression {

	private final NumericExpression change;
	private final int n;
	
	public PercentRank(NumericExpression e, int n) {
		super(functionRepresentation("PercentRank", e, n));
		this.change = new Change(e);
		this.n = n;
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		BigDecimal currentChangeValue = change.getValue(context, index); 
		if ( currentChangeValue == null ) {
			return null;			
		}
		int count = 0;
		for ( int i = index - n; i < index; i++ ) {
			BigDecimal previousChangeValue = change.getValue(context, i);
			if ( previousChangeValue == null ) { //sb impossible; we have current change
				return null;
			}
			MathContext mc = context.getMathContext();
			previousChangeValue = previousChangeValue.abs(mc);
			//TODO: check the use of abs() ; I think we need to compare magnitude of change
			currentChangeValue = currentChangeValue.abs(mc);
			
			if ( previousChangeValue.compareTo(currentChangeValue) < 0 ) {
				count++;
			}
		}
		double ratio = (double) count / n;
		return BigDecimal.valueOf(ratio * 100);
	}

	@Override
	public Set<NumericExpression> operands() {
		return Set.of(change);
	}

}
