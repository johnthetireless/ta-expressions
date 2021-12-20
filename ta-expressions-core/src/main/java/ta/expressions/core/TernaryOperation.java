package ta.expressions.core;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Objects of this class defer evaluation of the Java ternary operator.
 *
 */
public class TernaryOperation extends NumericExpression {
	
	private final BooleanExpression test;
	private final NumericExpression e1;
	private final NumericExpression e2;


	public TernaryOperation(BooleanExpression test, NumericExpression e1,
			NumericExpression e2) {
		super(test.representation() + " ? " + e1.representation() + " : " + e2.representation());
		this.test = test;
		this.e1 = e1;
		this.e2 = e2;
	}


	@Override
	public BigDecimal getValue(AnalysisContext context, int index) {
		return evaluate(context, index);
	}


	@Override
	public BigDecimal evaluate(AnalysisContext context, int index) {
		Boolean b = test.getValue(context, index);
//		BigDecimal v1 = e1.getValue(context, index);
//		BigDecimal v2 = e2.getValue(context, index);  divide by zero is possible here
		if ( b == null ) {
			return null;			
		}
		return b ? e1.getValue(context, index) : e2.getValue(context, index);
	}


	@Override
	public Set<NumericExpression> operands() {
//		Set<NumericExpression> s = new HashSet<>(test.terms());
		Set<NumericExpression> s = new HashSet<>();
		s.add(e1);
		s.add(e2);
		return s;
	}
	
	

}
