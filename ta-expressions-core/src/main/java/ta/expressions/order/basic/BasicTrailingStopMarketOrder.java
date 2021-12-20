package ta.expressions.order.basic;

import java.math.BigDecimal;
import java.util.Optional;

import ta.expressions.order.FillableOrder;
import ta.expressions.order.TrailingStopDelta;
import ta.expressions.order.TrailingStopMarketOrder;

public class BasicTrailingStopMarketOrder extends BasicTradingOrder implements TrailingStopMarketOrder {

	private final TrailingStopDelta stopDelta;
	
	private BigDecimal triggerPrice = null;
	
	public BasicTrailingStopMarketOrder(String symbol, Action action, TrailingStopDelta stopDelta, long timestamp) {
		super(symbol, action, timestamp);
		this.stopDelta = stopDelta;
	}

	@Override
	public Optional<FillableOrder> convertAt(BigDecimal price, long timestamp) {
		if ( isBuy() && buyAt(price) ) {
			return Optional.of(new BasicMarketOrder(symbol(), Action.BUY, timestamp));
		}
		else if ( isSell() && sellAt(price) ) {
			return Optional.of(new BasicMarketOrder(symbol(), Action.SELL, timestamp));
		}
		return Optional.empty();
	}
	
	public BigDecimal triggerPrice() {
		return triggerPrice;
	}

	protected boolean buyAt(BigDecimal price) {
		if ( triggerPrice == null ) {
			triggerPrice = price;
			return false;
		}
		else {
			boolean buy = stopDelta.buyAt(price, triggerPrice);
			if ( !buy ) {
				triggerPrice = triggerPrice.min(price);							
			}
			return buy;			
		}
	}

	protected boolean sellAt(BigDecimal price) {
		if ( triggerPrice == null ) {
			triggerPrice = price;
			return false;
		}
		else {
			boolean sell = stopDelta.sellAt(price, triggerPrice);
			if ( !sell ) {
				triggerPrice = triggerPrice.max(price);							
			}
			return sell;			
		}
	}
	
	@Override
	public TrailingStopDelta stopDelta() {
		return stopDelta;
	}

	@Override
	public String toString() {
		return "BasicTrailingStopMarketOrder [ action()=" + action() + " stopDelta=" + stopDelta + ", triggerPrice=" + triggerPrice
				+ " orderDate=" + date() + "]";
	}

	
}
