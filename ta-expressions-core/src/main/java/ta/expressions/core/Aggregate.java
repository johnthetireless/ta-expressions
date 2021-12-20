package ta.expressions.core;

import java.math.BigDecimal;

/**
 * An aggregate is the "unit of observation" or "unit of analysis".
 * These objects are sometimes called "bars" or "candles".
 * A sequence of aggregates provides the data needed to evaluate technical analysis expressions.
 * <p>
 * Historic data might be read from a file or retrieved from a URL.
 * The data for live trading might be aggregated from trades as part of a larger system.
 * The means by which aggregates are created is outside the scope of this library.
 * A later version may address this further.
 * <p>
 * Expressions depend on the OHLCV values and the order in which they occur.
 * A timestamp is included solely for use by a larger system.
 * None of the expressions rely on a timestamp; no effort is made to validate timestamps or use them in any way.
 * It is assumed aggregates occur in ascending order by timestamp, but no check is made.
 */
public interface Aggregate {

	BigDecimal open();
	BigDecimal high();
	BigDecimal low();
	BigDecimal close();
	BigDecimal volume();
	long timestamp();
}
