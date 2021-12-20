package ta.expressions.core;

/**
 * This is the base class for simple technical analysis "terms".  
 * <p>
 * This is a public class for now.  It will *probably* remain as such.
 * Keeping it public offers the opportunity for user-defined or ad-hoc terms.
 * Unless there is a reason to hide this class it will remain public.
 *
 */
public class BasicAnalyticFunction extends AnalyticFunction {

	private final NumericExpression formula;
	
	public BasicAnalyticFunction(String name, NumericExpression formula) {
		super(name);
		this.formula = formula;
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
