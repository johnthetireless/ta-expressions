package ta.expressions.order;

import java.math.BigDecimal;
import java.util.Optional;

public interface StopOrder extends TradingOrder {

	Optional<FillableOrder> convertAt(BigDecimal price, long timestamp);
}
