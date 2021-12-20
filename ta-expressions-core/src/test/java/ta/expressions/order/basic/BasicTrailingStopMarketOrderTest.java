package ta.expressions.order.basic;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ta.expressions.order.FillableOrder;
import ta.expressions.order.TradingOrder;
import ta.expressions.order.TrailingStopDelta;

class BasicTrailingStopMarketOrderTest {

	private BasicTrailingStopMarketOrder buy;
	private BasicTrailingStopMarketOrder sell;
	private TrailingStopDelta amountOfTen;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		amountOfTen = BasicTrailingStopDelta.amount(10.0);
		buy = new BasicTrailingStopMarketOrder("SPY", TradingOrder.Action.BUY, amountOfTen, 0);
		sell = new BasicTrailingStopMarketOrder("SPY", TradingOrder.Action.SELL, amountOfTen, 0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void buyStopWillNotConvertOnFirstPriceAndWillSetTrigger() {
		
		assert(buy.triggerPrice() == null);
		
		BigDecimal price = BigDecimal.valueOf(10.0);
		Optional<FillableOrder> fo = buy.convertAt(price, 0);
		assert(fo.isEmpty());
		
		assert(buy.triggerPrice().compareTo(price) == 0);
	}

}
