package ta.expressions.order.basic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ta.expressions.order.TradingOrder;

class BasicBrokerTest {

	BasicBroker broker;
	
	BasicMarketOrder buyMarket;
	BasicMarketOrder sellMarket;
	BasicLimitOrder buyLimit5;
	BasicLimitOrder buyLimit20;
	
	ClosePriceOnlyAggregate close10;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		broker = new BasicBroker("SPY");
		buyMarket = new BasicMarketOrder("SPY", TradingOrder.Action.BUY, 0);
		sellMarket = new BasicMarketOrder("SPY", TradingOrder.Action.SELL, 0);
		buyLimit5 = new BasicLimitOrder("SPY", TradingOrder.Action.BUY, 5.0, 0);
		buyLimit20 = new BasicLimitOrder("SPY", TradingOrder.Action.BUY, 20.0, 0);
		close10 = new ClosePriceOnlyAggregate(10.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void brokerHasNoOrderInitially() {
		assertEquals(broker.orders().size(), 0);
	}
	
	@Test
	void brokerWillQueueBuyOrderUntilFirstInput() {
		assertEquals(broker.orders().size(), 0);
		broker.accept(buyMarket);
		assertEquals(broker.orders().size(), 1);
	}
	
	@Test
	void brokerWillFillQueuedBuyOrderAfterReceivingFirstInput() {
		assertEquals(broker.orders().size(), 0);
		broker.accept(buyMarket);
		assertEquals(broker.orders().size(), 1);
		
		broker.accept(close10);
		assertEquals(broker.tradeNotifications().size(), 1);
		
		assertEquals(broker.tradeNotifications().get(0).order(), buyMarket);
	}
	
	@Test
	void brokerWillNotFillBuyLimit5At10() {		
		assertEquals(broker.tradeNotifications().size(), 0);
		assertEquals(broker.unfilledOrders().size(), 0);
		broker.accept(buyLimit5);
		assertEquals(broker.unfilledOrders().size(), 1);
		broker.accept(close10);
		assertEquals(broker.unfilledOrders().size(), 1);
		assertEquals(broker.tradeNotifications().size(), 0);
	}
	
	@Test
	void brokerWillFillBuyLimit20At10() {		
		assertEquals(broker.tradeNotifications().size(), 0);
		assertEquals(broker.unfilledOrders().size(), 0);
		broker.accept(buyLimit20);
		assertEquals(broker.unfilledOrders().size(), 1);
		broker.accept(close10);
		assertEquals(broker.unfilledOrders().size(), 0);
		assertEquals(broker.tradeNotifications().size(), 1);
	}
	
	@Test
	void buySell() {		
		
		assert(broker.position().notOpen());
		broker.accept(close10);
		broker.accept(buyMarket);
		assert(broker.position().isOpen());
		broker.accept(close10);
		broker.accept(sellMarket);
		assert(broker.position().notOpen());
		broker.accept(close10);
		broker.accept(buyMarket);
		assert(broker.position().isOpen());
		broker.accept(close10);
		broker.accept(sellMarket);
		assert(broker.position().notOpen());
	}
	
	

}
