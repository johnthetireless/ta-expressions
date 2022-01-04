package ta.expressions.core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;
import java.util.function.BiFunction;
/** 
 * Provides static factory methods to create arithmetic operations and math functions.
 * <p>
 * These are called by the NumericExpression class.  They are not meant for the public API.
 *
 */
class Arithmetic {
	
	//keywords
	public static final String MAX 			= "MAX";
	public static final String MIN 			= "MIN";
	public static final String ABS 			= "ABS";
	public static final String SQRT 		= "SQRT";
	public static final String NEGATION 	= "Negation";
	public static final String SIGN 		= "Sign";
	public static final String POW 			= "POW";
	public static final String LOG10 		= "Log10";
	
	public static final String[] KEYWORDS = {MAX,MIN,ABS,SQRT,NEGATION,SIGN,POW,LOG10};
	
	static NumericExpression sum(NumericExpression e1, NumericExpression e2) {
		return new BinaryOperation("+", (v1, v2, mc) -> v1.add(v2, mc), e1, e2);
	}

	static NumericExpression difference(NumericExpression e1, NumericExpression e2) {
		return new BinaryOperation("-", (v1, v2, mc) -> v1.subtract(v2, mc), e1, e2);
	}

	static NumericExpression product(NumericExpression e1, NumericExpression e2) {
		return new BinaryOperation("*", (v1, v2, mc) -> v1.multiply(v2, mc), e1, e2);
	}

	static NumericExpression quotient(NumericExpression e1, NumericExpression e2) {
		return new BinaryOperation("/", (v1, v2, mc) -> v1.divide(v2, mc), e1, e2);
	}

	static NumericExpression max(NumericExpression e1, NumericExpression e2) {
		return new BinaryFunction(MAX, (v1, v2, ignored) -> v1.max(v2), e1, e2);
	}

	static NumericExpression min(NumericExpression e1, NumericExpression e2) {
		return new BinaryFunction(MIN, (v1, v2, ignored) -> v1.min(v2), e1, e2);
	}
	
	static NumericExpression abs(NumericExpression e) {
		return new UnaryFunction(ABS, (v, mc) -> v.abs(mc), e);
	}

	static NumericExpression sqrt(NumericExpression e) {
		return new UnaryFunction(SQRT, (v, mc) -> v.sqrt(mc), e);
	}

	static NumericExpression negate(NumericExpression e) {
		return new UnaryFunction(NEGATION, (v, mc) -> v.negate(mc), e);
	}

	static NumericExpression signum(NumericExpression e) {
		return new Signum(e);
	}

	static NumericExpression pow(NumericExpression e, int n) {
		return new ExponentPower(e, n);
	}

	static NumericExpression log10(NumericExpression e) {
		return new Log10(e);
	}
	
	private Arithmetic() {}
	
	interface BigDecimalBinaryOperator {
		BigDecimal apply(BigDecimal v1, BigDecimal v2, MathContext mc);
	}
	
	static abstract class BinaryExpression extends ArithmeticExpression {
		private final BigDecimalBinaryOperator op;
		private final NumericExpression e1;
		private final NumericExpression e2;
		
		BinaryExpression(String representation, BigDecimalBinaryOperator op, NumericExpression e1, NumericExpression e2) {
			super(representation);
			this.op = op;
			this.e1 = e1;
			this.e2 = e2;
		}
		
		@Override
		public BigDecimal evaluate(AnalysisContext context, int index) {
			BigDecimal v1 = e1.getValue(context, index);
			BigDecimal v2 = e2.getValue(context, index);
			if ( v1 == null || v2 == null ) {
				return null;
			}
			return op.apply(v1, v2, context.getMathContext());
		}

		@Override
		public Set<NumericExpression> operands() {
			return Set.of(e1, e2);
		}
	}
	
	static class BinaryOperation extends BinaryExpression {		
		BinaryOperation(String symbol, BigDecimalBinaryOperator op, NumericExpression e1, NumericExpression e2) {
			super(operationRepresentation(symbol, e1, e2), op, e1, e2);
		}		
	}

	static class BinaryFunction extends BinaryExpression {		
		BinaryFunction(String symbol, BigDecimalBinaryOperator op, NumericExpression e1, NumericExpression e2) {
			super(functionRepresentation(symbol, e1, e2), op, e1, e2);
		}		
	}
	
	static class UnaryFunction extends AlgebraicExpression {
		private final BiFunction<BigDecimal, MathContext, BigDecimal> op;
		private final NumericExpression e;
		
		public UnaryFunction(String symbol, BiFunction<BigDecimal, MathContext, BigDecimal> op,
				NumericExpression e) {
			super(functionRepresentation(symbol, e));
			this.op = op;
			this.e = e;
		}

		@Override
		public BigDecimal evaluate(AnalysisContext context, int index) {
			BigDecimal v = e.getValue(context, index);
			if ( v == null  ) {
				return null;
			}
			return op.apply(v, context.getMathContext());
		}

		@Override
		public Set<NumericExpression> operands() {
			return Set.of(e);
		}
	}
	
	static class Signum extends AlgebraicExpression {
		private static final BigDecimal NEGATIVE_ONE = BigDecimal.ONE.negate();
		private final NumericExpression e;

		public Signum(NumericExpression e) {
			super(functionRepresentation(SIGN, e));
			this.e = e;
		}

		@Override
		public BigDecimal evaluate(AnalysisContext context, int index) {
			BigDecimal v = e.getValue(context, index);
			if ( v == null  ) {
				return null;
			}
			int sign = v.signum();
			switch ( sign ) {
			case - 1 : return NEGATIVE_ONE;
			case 0 : return BigDecimal.ZERO;
			case 1 : return BigDecimal.ONE;
			default : return null;  // impossible; signum() return -1,0 0r 1
			}
		}

		@Override
		public Set<NumericExpression> operands() {
			return Set.of(e);
		}
		
	}

	static class ExponentPower extends AlgebraicExpression {
		private final NumericExpression e;
		private final int n;
		
		public ExponentPower(NumericExpression e, int n) {
			super(functionRepresentation(POW, e, n));
			this.e = e;
			this.n = n;
		}
		
		@Override
		public BigDecimal evaluate(AnalysisContext context, int index) {
			BigDecimal v = e.getValue(context, index);
			if ( v == null  ) {
				return null;
			}
			return v.pow(n, context.getMathContext());
		}
		
		@Override
		public Set<NumericExpression> operands() {
			return Set.of(e);
		}
	
	}
	
	static class Log10 extends AlgebraicExpression {
		private final NumericExpression e;

		public Log10(NumericExpression e) {
			super(functionRepresentation(LOG10, e));
			this.e = e;
		}
		
		@Override
		public BigDecimal evaluate(AnalysisContext context, int index) {
			BigDecimal v = e.getValue(context, index);
			if ( v == null  ) {
				return null;
			}
			double d = v.doubleValue();
			return BigDecimal.valueOf(Math.log10(d));
		}
		
		@Override
		public Set<NumericExpression> operands() {
			return Set.of(e);
		}
	}
	
}
