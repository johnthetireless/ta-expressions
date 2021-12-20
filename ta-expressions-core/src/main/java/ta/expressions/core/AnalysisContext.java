package ta.expressions.core;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * This is the interface expressions depend on for their evaluation.
 * <p>
 * It provides:
 * <ul>
 * <li>access to input aggregates; used by the TA price and volume variables
 * <li>access to the values of numeric expressions at the current or a previous index; 
 * these may be cached or evaluated as needed
 * <li>access to the math context; used by lower-level calculations
 * </ul>
 *
 */
public interface AnalysisContext {

	Aggregate getInput(int index);
	BigDecimal getValue(NumericExpression e, int index);
	MathContext getMathContext();
}
