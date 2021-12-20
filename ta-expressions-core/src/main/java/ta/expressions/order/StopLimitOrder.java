package ta.expressions.order;

import java.math.BigDecimal;

public interface StopLimitOrder extends StopOrder {

	BigDecimal triggerPrice();
	BigDecimal limitPrice();
}
