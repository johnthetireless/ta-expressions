package ta.expressions.reversals;

import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;

public class ReversalPattern extends BooleanExpression {
	
	private final ReversalPatternType patternType;
	private final ReversalTurnType turnType;
	private final double nearPercent;
	private final BooleanExpression formula;
	
	public ReversalPattern(ReversalPatternType patternType, ReversalTurnType turnType,
			double nearPercent, BooleanExpression formula) {
		super(functionRepresentation(patternType.keyword() + "_" + turnType.keyword(), nearPercent));
		this.patternType = patternType;
		this.turnType = turnType;
		this.nearPercent = nearPercent;
		this.formula = formula;
	}

	
	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		return formula.evaluate(context, index);
	}

	@Override
	public Set<NumericExpression> terms() {
		return Set.of();
	}

	public ReversalPatternType patternType() {
		return patternType;
	}

	public ReversalTurnType turnType() {
		return turnType;
	}

	protected double nearPercent() {
		return nearPercent;
	}

	
	
}
