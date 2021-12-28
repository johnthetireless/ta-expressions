package ta.expressions.indicators.volume;

import ta.expressions.common.change.Change;
import ta.expressions.common.change.RateOfChange;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.Volume;

public class NegativeVolumeIndex extends AnalyticFunction {

	public static final String KEYWORD = "NVI";

	public static final NegativeVolumeIndex INSTANCE = new NegativeVolumeIndex();
	
	public static NegativeVolumeIndex fromString(String ignored) {
		return INSTANCE;
	}
	
	private final NumericExpression formula;
	
	private NegativeVolumeIndex() {
		super(KEYWORD);
		this.formula = new TernaryOperation(
				previous().isNull(),
				Constant.valueOf(1000),
				new TernaryOperation(
						new Change(Volume.INSTANCE, 1).lessThan(0),
						previous().plus(new RateOfChange(ClosePrice.INSTANCE, 1).multipliedBy(100)),
						previous()
						)
				);
	}

	@Override
	protected NumericExpression getFormula() {
		return formula;
	}
	
}
