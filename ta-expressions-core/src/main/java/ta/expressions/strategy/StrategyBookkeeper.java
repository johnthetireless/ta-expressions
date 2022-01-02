package ta.expressions.strategy;

public class StrategyBookkeeper extends Bookkeeper {

	private final Strategy strategy;

	public StrategyBookkeeper(Strategy strategy) {
		super();
		this.strategy = strategy;
	}
	
	public Strategy strategy() {
		return strategy;
	}
	
	@Override
	public String toString() {
		return "StrategyBookkeeper [strategy=" + strategy.name() + ", activeBooks=" + activeBookCount() + "]";
	}
	

}
