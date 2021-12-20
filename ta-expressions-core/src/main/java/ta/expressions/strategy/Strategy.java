package ta.expressions.strategy;

import java.util.List;

import ta.expressions.core.BooleanExpression;
import ta.expressions.order.Order;
import ta.expressions.order.TradingOrder;
import ta.expressions.order.TradingPosition;
import ta.expressions.order.basic.BasicMarketOrder;
import ta.expressions.order.basic.BasicTrailingStopDelta;
import ta.expressions.order.basic.BasicTrailingStopMarketOrder;
import ta.expressions.signal.Signal;

public class Strategy {

	private final BooleanExpression e1;
	private final BooleanExpression e2;
	
	public Strategy(BooleanExpression e1, BooleanExpression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	public List<Order> act(Signal signal, TradingPosition position) {

		if ( signal.causedBy(e1) ) {
			if ( !position.isOpen() ) {
				Order order = new BasicMarketOrder(position.symbol(), TradingOrder.Action.BUY, signal.timestamp());
				Order stop = new BasicTrailingStopMarketOrder(position.symbol(), TradingOrder.Action.SELL, 
						BasicTrailingStopDelta.amount(7.0), signal.timestamp());
				return List.of(order, stop);				
//				return List.of(order);				
			}
		}
		else if ( signal.causedBy(e2) ) {
			if ( position.isOpen() ) {
				Order order = new BasicMarketOrder(position.symbol(), TradingOrder.Action.SELL, signal.timestamp());
				return List.of(order);				
			}
		}
		
		return List.of();
	}
	
}
