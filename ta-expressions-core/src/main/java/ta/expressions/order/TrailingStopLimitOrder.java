package ta.expressions.order;

import java.math.BigDecimal;

public interface TrailingStopLimitOrder extends StopOrder {

	TrailingStopDelta stopDelta();
	BigDecimal limitDelta();
}
