package ta.expressions.order.basic;

import java.math.BigDecimal;
import java.util.Optional;

import ta.expressions.order.FillableOrder;
import ta.expressions.order.StopLimitOrder;

public class BasicStopLimitOrder extends BasicTradingOrder implements StopLimitOrder {

	private final BigDecimal triggerPrice;
	private final BigDecimal limitPrice;
	
	public BasicStopLimitOrder(String symbol, Action action, BigDecimal triggerPrice,
			BigDecimal limitPrice, long timestamp) {
		super(symbol, action, timestamp);
		this.triggerPrice = triggerPrice;
		this.limitPrice = limitPrice;
	}
	
	@Override
	public Optional<FillableOrder> convertAt(BigDecimal price, long timestamp) {
		if ( isBuy() && buyAt(price) ) {
			return Optional.of(new BasicLimitOrder(symbol(), Action.BUY, limitPrice, timestamp));
		}
		else if ( isSell() && sellAt(price) ) {
			return Optional.of(new BasicLimitOrder(symbol(), Action.SELL, limitPrice, timestamp));
		}
		return Optional.empty();
	}

	@Override
	public BigDecimal triggerPrice() {
		return triggerPrice;
	}

	@Override
	public BigDecimal limitPrice() {
		return limitPrice;
	}

	protected boolean buyAt(BigDecimal price) {
		return price.compareTo(triggerPrice) <= 0;
	}

	protected boolean sellAt(BigDecimal price) {
		return price.compareTo(triggerPrice) >= 0;
	}

	@Override
	public String toString() {
		return "BasicStopLimitOrder [action()=" + action() + " triggerPrice=" + triggerPrice 
						+ ", limitPrice=" + limitPrice + "]";
	}
	
	

}
