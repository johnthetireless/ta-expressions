package ta.expressions.core;

import java.math.BigDecimal;
import java.math.MathContext;

public interface AnalysisContext {
	
	Aggregate getInput(int index);
	BigDecimal getValue(NumericExpression e, int index);
	MathContext getMathContext();
	
	
}
