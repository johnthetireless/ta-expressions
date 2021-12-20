package ta.expressions.order.basic;

import java.math.BigDecimal;
import java.util.Optional;

import ta.expressions.order.FillableOrder;
import ta.expressions.order.TrailingStopDelta;
import ta.expressions.order.TrailingStopLimitOrder;

public class BasicTrailingStopLimitOrder extends BasicTradingOrder implements TrailingStopLimitOrder {

	private final TrailingStopDelta stopDelta;
	private final BigDecimal limitDelta;
	
	private BigDecimal triggerPrice = null;
	
	public BasicTrailingStopLimitOrder(String symbol, Action action, TrailingStopDelta stopDelta,
			BigDecimal limitDelta, BigDecimal initialTriggerPrice, long timestamp) {
		super(symbol, action, timestamp);
		this.stopDelta = stopDelta;
		this.limitDelta = limitDelta;
		this.triggerPrice = initialTriggerPrice;
	}

	@Override
	public Optional<FillableOrder> convertAt(BigDecimal price, long timestamp) {
		if ( isBuy() && buyAt(price) ) {
			return Optional.of(new BasicLimitOrder(symbol(), Action.BUY, price.subtract(limitDelta), timestamp));
		}
		else if ( isSell() && sellAt(price) ) {
			return Optional.of(new BasicLimitOrder(symbol(), Action.SELL, price.add(limitDelta), timestamp));
		}
		return Optional.empty();
	}


	protected boolean buyAt(BigDecimal price) {
		boolean buy = stopDelta.buyAt(price, triggerPrice);
		if ( !buy ) {
			triggerPrice = triggerPrice.max(price);			
		}
		return buy;
	}

	protected boolean sellAt(BigDecimal price) {
		boolean sell = stopDelta.sellAt(price, triggerPrice);
		if ( !sell ) {
			triggerPrice = triggerPrice.min(price);			
		}
		return sell;
	}

	
	@Override
	public TrailingStopDelta stopDelta() {
		return stopDelta;
	}

	@Override
	public BigDecimal limitDelta() {
		return limitDelta;
	}

}
