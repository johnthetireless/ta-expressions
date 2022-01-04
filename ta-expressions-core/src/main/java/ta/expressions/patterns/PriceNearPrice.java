package ta.expressions.patterns;

import java.util.Set;

import ta.expressions.core.AnalysisContext;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.PriceRange;

public class PriceNearPrice extends BooleanExpression {

	private final BooleanExpression formula;
	
	public PriceNearPrice(NumericExpression price1, NumericExpression price2, double threshold) {
		super(functionRepresentation("Near", price1, price2, threshold));
		NumericExpression distance = price1.minus(price2).abs();
		this.formula = distance.lessOrEqual(PriceRange.INSTANCE.multipliedBy(threshold));
		
	}
	
	public BooleanExpression formula() {
		return formula;
	}

	@Override
	public Boolean evaluate(AnalysisContext context, int index) {
		return formula.evaluate(context, index);
	}

	@Override
	public Set<NumericExpression> terms() {
		return formula.terms();
	}

}
