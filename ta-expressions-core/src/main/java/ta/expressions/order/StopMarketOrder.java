package ta.expressions.order;

import java.math.BigDecimal;

public interface StopMarketOrder extends StopOrder {

	BigDecimal triggerPrice();
}
