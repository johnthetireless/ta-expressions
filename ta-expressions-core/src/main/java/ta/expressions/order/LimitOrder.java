package ta.expressions.order;

import java.math.BigDecimal;

public interface LimitOrder extends FillableOrder {

	BigDecimal limitPrice();
}
