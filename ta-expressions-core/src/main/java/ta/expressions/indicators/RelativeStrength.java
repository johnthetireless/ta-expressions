package ta.expressions.indicators;

import ta.expressions.common.change.Gain;
import ta.expressions.common.change.Loss;
import ta.expressions.common.stats.MMA;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;

public class RelativeStrength extends AnalyticFunction {

	private final NumericExpression formula;

	public RelativeStrength(int n) {
		this(ClosePrice.INSTANCE, n);
	}

	public RelativeStrength(NumericExpression e, int n) {
		super(functionRepresentation("RS", e, n));
		NumericExpression averageGain = new MMA(new Gain(e), n);
		NumericExpression averageLoss = new MMA(new Loss(e), n);
		this.formula = new TernaryOperation(
				averageLoss.isZero(), 
				new TernaryOperation(
						averageGain.isZero(),
						Constant.valueOf(100),  //not sure about this
//						Constant.valueOf(0),
						Constant.valueOf(0)),
				averageGain.dividedBy(averageLoss));
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}

}
