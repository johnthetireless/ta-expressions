package ta.expressions.order;

import java.math.BigDecimal;

public interface TrailingStopDelta {

	TrailingStopDeltaType type();
	BigDecimal value();
	
	
	boolean buyAt(BigDecimal price, BigDecimal triggerPrice);
	
	boolean sellAt(BigDecimal price, BigDecimal triggerPrice);	
	
	
}
