package ta.expressions.demo.strategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import ta.expressions.core.Aggregate;

public class AggregateDataset {

	private final String name;
	private final List<Aggregate> data;
	private final int startIndex;
	
	public AggregateDataset(String name, List<Aggregate> data) {
		this(name, data, 0);
	}
	
	public AggregateDataset(String name, List<Aggregate> data, int startIndex) {
		this.name = name;
		this.data = data;
		this.startIndex = startIndex;
	}

	public String name() {
		return name;
	}

	public List<Aggregate> data() {
		return data;
	}

	public int startIndex() {
		return startIndex;
	}
	
	public int size() {
		return data.size();
	}
	
	public BigDecimal firstClose() {
		return data.get(0).close();
	}
	
	public BigDecimal lastClose() {
		return data.get(data.size() - 1).close();
	}

	public BigDecimal priceChange() {
		return lastClose().subtract(firstClose(), MathContext.DECIMAL64);
	}
	
}
