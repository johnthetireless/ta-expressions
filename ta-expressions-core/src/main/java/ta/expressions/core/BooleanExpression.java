package ta.expressions.core;

import java.util.Set;

/**
 * Base class for expressions that evaluate to true/false.
 * <p>
 * Boolean expressions can be used to trigger trading rules.
 * <p>
 * Boolean expressions are directly or indirectly based on numeric expressions.  
 * <p>
 * terms() provides a set of the highest-level numeric expressions.  
 * This can be useful when examining signals.
 *
 */
public abstract class BooleanExpression extends AbstractExpression<Boolean> {

	public static final String[] KEYWORDS = {LogicalOperation.AND,LogicalOperation.OR,NotOperation.KEYWORD};
	
	public BooleanExpression(String representation) {
		super(representation);
	}

	@Override
	public Boolean getValue(AnalysisContext context, int index) {
		return evaluate(context, index);
	}

	public BooleanExpression and(BooleanExpression other) {
		return LogicalOperation.and(this, other);
	}
	
	public BooleanExpression or(BooleanExpression other) {
		return LogicalOperation.or(this, other);
	}
	
	public BooleanExpression not() {
		return new NotOperation(this);
	}
	
	public abstract Set<NumericExpression> terms();
}
