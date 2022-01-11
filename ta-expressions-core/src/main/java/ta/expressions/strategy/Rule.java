package ta.expressions.strategy;

import ta.expressions.core.BooleanExpression;
import ta.expressions.signal.Signal;

/**
 * This is a design idea.  It isn't used anywhere at present.
 * The idea is... there are 4 "types" of rule:
 * <ol>
 * <li> long entry
 * <li> long exit
 * <li> short entry
 * <li> short exit
 * </ol>
 * These may overlap in some strategies.
 * <p>
 * A "rule" will be triggered by a boolean expression.
 * It will "recommend" zero or more "actions": buy at market, sell at limit... etc.
 * Such recommendations will need to consider the current position.
 * 
 */
public class Rule {

	private final PositionType positionType;
	private final RuleType ruleType;
	private final BooleanExpression expression;
	
	public Rule(PositionType positionType, RuleType ruleType, BooleanExpression expression) {
		this.positionType = positionType;
		this.ruleType = ruleType;
		this.expression = expression;
	}
	
	public StrategyAction actOn(Signal signal) {
		if ( signal.causedBy(expression) ) {
			return new StrategyAction(positionType, ruleType, signal.index());
		}
		return null;
	}
	
	
}
