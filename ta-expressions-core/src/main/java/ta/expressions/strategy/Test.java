package ta.expressions.strategy;

import java.util.List;
import java.util.Set;

import ta.expressions.app.CsvReader;
import ta.expressions.common.stats.SMA;
import ta.expressions.core.Aggregate;
import ta.expressions.core.BooleanExpression;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.variables.ClosePrice;
import ta.expressions.order.basic.BasicBroker;
import ta.expressions.signal.SignalGenerator;

public class Test {

	public static void main(String[] args) {
		
		NumericExpression price = ClosePrice.INSTANCE;
		NumericExpression sma50 = new SMA(price, 10);
		NumericExpression sma200 = new SMA(price, 50);
		
		BooleanExpression entry = sma50.crossedOver(sma200);
		BooleanExpression exit = sma50.crossedUnder(sma200);
		
		String symbol = "RY";

		Strategy strategy = new Strategy(entry, exit);
		
		BasicBroker broker = new BasicBroker(symbol);
		StrategyExecution exec = new StrategyExecution(strategy, broker);
				
		SignalGenerator sg = new SignalGenerator(
				Set.of(entry, exit), exec);
		
		List<Aggregate> aggs = CsvReader.readFile(symbol + ".csv");
		for ( int i = 0; i < aggs.size(); i++ ) {
			Aggregate a = aggs.get(i);
			broker.accept(a);
			sg.accept(a);
		}		
		
		System.out.println("Orders (" + broker.orders().size() + ")............");
		broker.orders().forEach(System.out::println);
		System.out.println();
		
		System.out.println("Trade Notifications (" + broker.tradeNotifications().size() + ")............");
		broker.tradeNotifications().forEach(System.out::println);
		System.out.println();
		
		System.out.println("Unfilled orders (" + broker.unfilledOrders().size() + ")............");
		broker.unfilledOrders().forEach(System.out::println);
		System.out.println();
		
		System.out.println("Stopped orders (" + broker.stoppedOrders().size() + ")............");
		broker.stoppedOrders().forEach(System.out::println);
		System.out.println();
		
		Aggregate a = aggs.get(aggs.size() - 1);
		System.out.println("Last price: " + a.close());

		System.out.println("Actions (" + broker.actions().size() + ")............");
		broker.actions().forEach(System.out::println);
		System.out.println();

		System.out.println("Total sales (" + broker.salesNotifications().size() + "):     " + broker.totalSales());
		System.out.println("Total purchases (" + broker.purchaseNotifications().size() + "):     " + broker.totalPurchases());
		System.out.println("                 -----------");
		System.out.println("Total profit:    " + broker.totalProfit());
		
		System.out.println("Trade Notifications (" + broker.tradeNotifications().size() + ")............");
		
		
	}

}
