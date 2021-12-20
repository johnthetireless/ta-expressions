package ta.expressions.core;

import java.math.BigDecimal;
import java.util.Set;

/**
 * This is the base class for most technical analysis calculations.
 * <p>
 * Most numeric expressions can be defined in terms of simpler expressions.
 * Almost all common and TA expressions are defined with a simple "formula".
 * This class groups them to simplify evaluation.
 * <p>
 * The equation() method provides a string like:
 * <p>
 * <code>
 * MedianPrice = (High + Low) / 2
 *<code>
 */
public abstract class AnalyticFunction extends AnalyticExpression {

	public AnalyticFunction(String representation) {
		super(representation);
	}

	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		NumericExpression formula = getFormula();
		if ( formula == null ) {
			//TODO; throw an exception; define one or 2 for the API, maybe
			System.out.println("null formula " + this);
		}
		return getFormula().getValue(context, index);
	}
	
	protected abstract NumericExpression getFormula();
	
	public String equation() {
		return representation() + " = " + getFormula().representation();
	}

	@Override
	public Set<NumericExpression> operands() {
//		return getFormula().operands();
		return Set.of(getFormula());
	}
	
	

}
