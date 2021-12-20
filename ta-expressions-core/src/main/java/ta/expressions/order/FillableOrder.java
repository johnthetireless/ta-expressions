package ta.expressions.order;

import java.math.BigDecimal;

public interface FillableOrder extends TradingOrder {

	boolean fillAt(BigDecimal price);
	
}
