package ta.expressions.strategy;

public class StrategyAction {
	
	public static StrategyAction longEntry(int index) {
		return new StrategyAction(PositionType.LONG, RuleType.ENTRY, index);
	}
	
	public static StrategyAction longExit(int index) {
		return new StrategyAction(PositionType.LONG, RuleType.EXIT, index);
	}
	
	public static StrategyAction shortEntry(int index) {
		return new StrategyAction(PositionType.SHORT, RuleType.ENTRY, index);
	}
	
	public static StrategyAction shortExit(int index) {
		return new StrategyAction(PositionType.SHORT, RuleType.EXIT, index);
	}
	
	private final PositionType positionType;
	private final RuleType ruleType;
	private final int index;
	
	public StrategyAction(PositionType positionType, RuleType ruleType, int index) {
		this.positionType = positionType;
		this.ruleType = ruleType;
		this.index = index;
	}
	
	
	
}
