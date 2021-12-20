package ta.expressions.order.basic;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ta.expressions.order.TrailingStopDelta;

class BasicTrailingStopDeltaTest {

	private TrailingStopDelta amountOfTen;
	
	@BeforeEach
	void setUp() throws Exception {
		amountOfTen = BasicTrailingStopDelta.amount(10.0);
	}

	@Test
	void doNotBuyAt50WhenTriggerIs55AndDeltaAmountIs10() {
		BigDecimal price = BigDecimal.valueOf(50.0);
		BigDecimal triggerPrice = BigDecimal.valueOf(55.0);
		boolean result = amountOfTen.buyAt(price, triggerPrice);
		assert(!result);
	}

	@Test
	void buyAt70WhenTriggerIs55AndDeltaAmountIs10() {
		BigDecimal price = BigDecimal.valueOf(70.0);
		BigDecimal triggerPrice = BigDecimal.valueOf(55.0);
		boolean result = amountOfTen.buyAt(price, triggerPrice);
		assert(result);
	}

	@Test
	void buyAt65WhenTriggerIs55AndDeltaAmountIs10() {
		BigDecimal price = BigDecimal.valueOf(65.0);
		BigDecimal triggerPrice = BigDecimal.valueOf(55.0);
		boolean result = amountOfTen.buyAt(price, triggerPrice);
		assert(result);
	}

	@Test
	void doNotBuyAt64WhenTriggerIs55AndDeltaAmountIs10() {
		BigDecimal price = BigDecimal.valueOf(64.0);
		BigDecimal triggerPrice = BigDecimal.valueOf(55.0);
		boolean result = amountOfTen.buyAt(price, triggerPrice);
		assert(!result);
	}

	@Test
	void sellAt50WhenTriggerIs75AndDeltaAmountIs10() {
		BigDecimal price = BigDecimal.valueOf(50.0);
		BigDecimal triggerPrice = BigDecimal.valueOf(75.0);
		boolean result = amountOfTen.sellAt(price, triggerPrice);
		assert(result);
	}

	@Test
	void sellAt64WhenTriggerIs75AndDeltaAmountIs10() {
		BigDecimal price = BigDecimal.valueOf(64.0);
		BigDecimal triggerPrice = BigDecimal.valueOf(75.0);
		boolean result = amountOfTen.sellAt(price, triggerPrice);
		assert(result);
	}

	@Test
	void sellAt65WhenTriggerIs75AndDeltaAmountIs10() {
		BigDecimal price = BigDecimal.valueOf(65.0);
		BigDecimal triggerPrice = BigDecimal.valueOf(75.0);
		boolean result = amountOfTen.sellAt(price, triggerPrice);
		assert(result);
	}

	@Test
	void doNotellAt66WhenTriggerIs75AndDeltaAmountIs10() {
		BigDecimal price = BigDecimal.valueOf(66.0);
		BigDecimal triggerPrice = BigDecimal.valueOf(75.0);
		boolean result = amountOfTen.sellAt(price, triggerPrice);
		assert(!result);
	}
}
