package ta.expressions.core;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A numeric expression defers an operation that produces a BigDecimal result.
 * <p>
 * Variables like ClosePrice.INSTANCE are numeric expressions, 
 * as are the objects created when methods like plus() and minus() are invoked.
 * <p>
 * Low-level subclasses of NumericExpression perform arithmetic, math and basic statistical functions.
 * <p>
 * Most of the calculations used in technical analysis are formed at a higher level, 
 * building upon the arithmetic and stats expressions.
 * <p>
 *
 */
public abstract class NumericExpression extends AbstractExpression<BigDecimal> {
	
	public static final String[] ARITHMETIC_KEYWORDS = Arithmetic.KEYWORDS;

	public NumericExpression(String representation) {
		super(representation); 
	}

	@Override
	public BigDecimal getValue(AnalysisContext context, int index) {
		return context.getValue(this, index);
	}
	
	public NumericExpression plus(NumericExpression other) {
		return Arithmetic.sum(this, other);
	}

	public NumericExpression plus(Number n) {
		return plus(Constant.valueOf(n));
	}

	public NumericExpression minus(NumericExpression other) {
		return Arithmetic.difference(this, other);
	}

	public NumericExpression minus(Number n) {
		return minus(Constant.valueOf(n));
	}

	public NumericExpression multipliedBy(NumericExpression other) {
		return Arithmetic.product(this, other);
	}

	public NumericExpression multipliedBy(Number n) {
		return multipliedBy(Constant.valueOf(n));
	}

	public NumericExpression dividedBy(NumericExpression other) {
		return Arithmetic.quotient(this, other);
	}

	public NumericExpression dividedBy(Number n) {
		return dividedBy(Constant.valueOf(n));
	}
	
	public NumericExpression divideOrZero(NumericExpression other) {
		return new TernaryOperation(other.equalTo(0), Constant.valueOf(0), this.dividedBy(other));
	}

	public NumericExpression max(NumericExpression other) {
		return Arithmetic.max(this, other);
	}

	public NumericExpression max(Number n) {
		return max(Constant.valueOf(n));
	}

	public NumericExpression min(NumericExpression other) {
		return Arithmetic.min(this, other);
	}

	public NumericExpression min(Number n) {
		return min(Constant.valueOf(n));
	}
	
	public NumericExpression abs() {
		return Arithmetic.abs(this);
	}

	public NumericExpression negation() {
		return Arithmetic.negate(this);
	}

	public NumericExpression sqrt() {
		return Arithmetic.sqrt(this);
	}
	
	public NumericExpression signum() {
		return Arithmetic.signum(this);
	}
	
	public NumericExpression pow(int n) {
		return Arithmetic.pow(this, n);
	}

	public NumericExpression squared() {
		return pow(2);
	}
	
	public NumericExpression log10() {
		return Arithmetic.log10(this);
	}
	
	public BooleanExpression greaterThan(NumericExpression other) {
		return RelationalOperation.greaterThan(this, other);
	}
	
	public BooleanExpression greaterThan(Number n) {
		return greaterThan(Constant.valueOf(n));
	}
	
	public BooleanExpression lessThan(NumericExpression other) {
		return RelationalOperation.lessThan(this, other);
	}
	
	public BooleanExpression lessThan(Number n) {
		return lessThan(Constant.valueOf(n));
	}
	
	public BooleanExpression greaterOrEqual(NumericExpression other) {
		return RelationalOperation.greaterOrEqual(this, other);
	}
	
	public BooleanExpression greaterOrEqual(Number n) {
		return greaterOrEqual(Constant.valueOf(n));
	}
	
	public BooleanExpression lessOrEqual(NumericExpression other) {
		return RelationalOperation.lessOrEqual(this, other);
	}
	
	public BooleanExpression lessOrEqual(Number n) {
		return lessOrEqual(Constant.valueOf(n));
	}
	
	public BooleanExpression equalTo(NumericExpression other) {
		return RelationalOperation.equalTo(this, other);
	}
	
	public BooleanExpression equalTo(Number n) {
		return equalTo(Constant.valueOf(n));
	}
	
	public BooleanExpression notEqualTo(NumericExpression other) {
		return RelationalOperation.notEqualTo(this, other);
	}
	
	public BooleanExpression notEqualTo(Number n) {
		return notEqualTo(Constant.valueOf(n));
	}
	
	public BooleanExpression crossedOver(NumericExpression other) {
		return Cross.over(this, other);
	}
		
	public BooleanExpression crossedOver(Number n) {
		return crossedOver(Constant.valueOf(n));
	}
	
	public BooleanExpression crossedUnder(NumericExpression other) {
		return Cross.under(this, other);
	}
		
	public BooleanExpression crossedUnder(Number n) {
		return crossedUnder(Constant.valueOf(n));
	}
	
	public BooleanExpression isNull() {
		return new IsNull(this);
	}
	
	public NumericExpression previous(int n) {
		return PreviousValue.of(this, n);
	}
	
	public NumericExpression previous() {
		return previous(1);
	}
	
	public abstract Set<NumericExpression> operands();
	
	
	public Set<NumericExpression> subexpressions() {
		Set<NumericExpression> s = new HashSet<>();
		for ( NumericExpression e : operands() ) {
			s.add(e);
			s.addAll(e.subexpressions());
		}
		return s;
	}
	
	public boolean isConstant() {
		return false;
	}
	
}
