package ta.expressions.core;

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
