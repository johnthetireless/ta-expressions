package ta.expressions.indicators.volume;

import ta.expressions.common.change.Change;
import ta.expressions.common.change.RateOfChange;
import ta.expressions.core.AnalyticFunction;
import ta.expressions.core.Constant;
import ta.expressions.core.NumericExpression;
import ta.expressions.core.TernaryOperation;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.indicators.variables.Volume;

//TODO: this is a mirror of NVI; its probably right, but I haven't seen a formula
public class PositiveVolumeIndex extends AnalyticFunction {

	public static final String KEYWORD = "NVI";

	public static final PositiveVolumeIndex INSTANCE = new PositiveVolumeIndex();
	
	public static PositiveVolumeIndex fromString(String ignored) {
		return INSTANCE;
	}
	
	private final NumericExpression formula;
	
	private PositiveVolumeIndex() {
		super(KEYWORD);
		this.formula = new TernaryOperation(
				previous().isNull(),
				Constant.valueOf(1000),
				new TernaryOperation(
						new Change(Volume.INSTANCE, 1).greaterThan(0),
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
