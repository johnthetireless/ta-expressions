package ta.expressions.order.basic;

import java.util.List;

import ta.expressions.app.CsvReader;
import ta.expressions.core.Aggregate;
import ta.expressions.order.Broker;
import ta.expressions.order.Order;
import ta.expressions.order.TradeNotification;
import ta.expressions.order.TradingOrder;
import ta.expressions.order.TradingOrder.Action;

public class Test {

	public static void main(String[] args) {

		String symbol = "TD";
		
		BasicBroker broker = new BasicBroker(symbol);
		
		List<Aggregate> aggs = CsvReader.readFile(symbol + ".csv");
		
		Aggregate a = aggs.get(0);
		broker.accept(a);
		
		TradingOrder market = new BasicMarketOrder(symbol, Action.BUY, a.timestamp());
		broker.accept(market);
		dump(0, a, broker);

		a = aggs.get(1);
		broker.accept(a);
		dump(1, a, broker);
		
		TradingOrder limit = new BasicLimitOrder(symbol, Action.BUY, 56.6, a.timestamp());
		broker.accept(limit);
		dump(1, a, broker);

		a = aggs.get(2);
		broker.accept(a);
		dump(2, a, broker);
		
//		for ( int i = 2; i < aggs.size(); i++ ) {
//			broker.accept(aggs.get(i));
//		}
		


	}
	
	static void dump(int i, Aggregate a, Broker broker) {
		System.out.println("i=" + i + " " + a);
		System.out.println("Orders........");
		for ( Order o : broker.orders() ) {
			System.out.println(o);
		}
		System.out.println();

		System.out.println("Trade Notifications........");
		for ( TradeNotification n : broker.tradeNotifications() ) {
			System.out.println(n);
		}
		System.out.println();
	}
}
