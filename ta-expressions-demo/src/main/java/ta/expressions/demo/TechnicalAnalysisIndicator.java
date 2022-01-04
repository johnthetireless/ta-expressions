package ta.expressions.demo;

import java.util.function.Function;

import ta.expressions.common.stats.Slope;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.AccelerationDeceleration;
import ta.expressions.indicators.AwesomeOscillator;
import ta.expressions.indicators.BalanceOfPower;
import ta.expressions.indicators.CCI;
import ta.expressions.indicators.CMO;
import ta.expressions.indicators.ConnorsRSI;
import ta.expressions.indicators.DEMA;
import ta.expressions.indicators.EMV;
import ta.expressions.indicators.ForceIndex;
import ta.expressions.indicators.MassIndex;
import ta.expressions.indicators.MoneyFlowIndex;
import ta.expressions.indicators.RSI;
import ta.expressions.indicators.TEMA;
import ta.expressions.indicators.TRIX;
import ta.expressions.indicators.TrueStrengthIndex;
import ta.expressions.indicators.TrendIntensityIndex;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.adx.ADXR;
import ta.expressions.indicators.adx.MinusDI;
import ta.expressions.indicators.adx.PlusDI;
import ta.expressions.indicators.aroon.AroonDown;
import ta.expressions.indicators.aroon.AroonOscillator;
import ta.expressions.indicators.aroon.AroonUp;
import ta.expressions.indicators.bollinger.BollingerBandwidth;
import ta.expressions.indicators.bollinger.BollingerLowerBand;
import ta.expressions.indicators.bollinger.BollingerMiddleBand;
import ta.expressions.indicators.bollinger.BollingerPercentB;
import ta.expressions.indicators.bollinger.BollingerUpperBand;
import ta.expressions.indicators.chandelier.ChandlelierExitLong;
import ta.expressions.indicators.chandelier.ChandlelierExitShort;
import ta.expressions.indicators.keltner.KeltnerLowerChannel;
import ta.expressions.indicators.keltner.KeltnerMiddleChannel;
import ta.expressions.indicators.keltner.KeltnerUpperChannel;
import ta.expressions.indicators.macd.MACD;
import ta.expressions.indicators.macd.MACDHistogram;
import ta.expressions.indicators.macd.MACDSignal;
import ta.expressions.indicators.macd.PPO;
import ta.expressions.indicators.macd.PPOHistogram;
import ta.expressions.indicators.macd.PPOSignal;
import ta.expressions.indicators.stochastics.StochasticOscillator;
import ta.expressions.indicators.stochastics.StochasticOscillatorSignal;
import ta.expressions.indicators.stochastics.StochasticRSI;
import ta.expressions.indicators.stochastics.StochasticMomentumIndex;
import ta.expressions.indicators.ultimate.UltimateOscillator;
import ta.expressions.indicators.variables.Volume;
import ta.expressions.indicators.volatility.ATR;
import ta.expressions.indicators.volatility.ChaikinVolatility;
import ta.expressions.indicators.volatility.ChoppinessIndex;
import ta.expressions.indicators.volatility.Volatility;
import ta.expressions.indicators.volume.AccumulationDistribution;
import ta.expressions.indicators.volume.ChaikinMoneyFlow;
import ta.expressions.indicators.volume.ChaikinOscillator;
import ta.expressions.indicators.volume.NegativeVolumeIndex;
import ta.expressions.indicators.volume.OnBalanceVolume;

public enum TechnicalAnalysisIndicator {
	
	ACCUMULATION_DISTRIBUTION(
			AccumulationDistribution.KEYWORD, 
			s -> AccumulationDistribution.INSTANCE,
			"Accumulation Distribution Line"
			),

	ACCELERATION_DECELERATION(
			AccelerationDeceleration.KEYWORD, 
			AccelerationDeceleration::fromString, 
			"Acceleration/Decelation"
			),

	AVERAGE_DIRECTIONAL_INDEX(
			ADX.KEYWORD, 
			ADX::fromString, 
			"Average Directional Index"
			),

	AVERAGE_DIRECTIONAL_INDEX_RATING(
			ADXR.KEYWORD, 
			ADXR::fromString, 
			"Average Directional Index Rating"
			),

	AVERAGE_TRUE_RANGE(
			ATR.KEYWORD, 
			ATR::fromString, 
			"Average True Range"
			),

	AWESOME_OSCILLATOR(
			AwesomeOscillator.KEYWORD, 
			AwesomeOscillator::fromString, 
			"Awesome Oscillator"
			),
	
	AROON_OSCILLATOR(
			AroonOscillator.KEYWORD, 
			AroonOscillator::fromString, 
			"Aroon Oscillator"
			),
	
	AROON_UP(
			AroonUp.KEYWORD, 
			AroonUp::fromString, 
			"Aroon Up"
			),
	
	AROON_DOWN(
			AroonDown.KEYWORD, 
			AroonDown::fromString, 
			"Aroon Down"
			),
	
	BOLLINGER_UPPER_BAND(
			BollingerUpperBand.KEYWORD, 
			BollingerUpperBand::fromString, 
			"Bollinger Upper Band"
			),	

	BOLLINGER_MIDDLE_BAND(
			BollingerMiddleBand.KEYWORD, 
			BollingerMiddleBand::fromString, 
			"Bollinger Middle Band"
			),	

	BOLLINGER_LOWER_BAND(
			BollingerLowerBand.KEYWORD, 
			BollingerLowerBand::fromString, 
			"Bollinger Lower Band"
			),	

	BOLLINGER_PERCENTB(
			BollingerPercentB.KEYWORD, 
			BollingerPercentB::fromString, 
			"Bollinger Bands %B"
			),	

	BOLLINGER_BANDWIDTH(
			BollingerBandwidth.KEYWORD, 
			BollingerBandwidth::fromString, 
			"Bollinger Bandwidth"
			),	

	BALANCE_OF_POWER(
			BalanceOfPower.KEYWORD, 
			BalanceOfPower::fromString, 
			"Balance of Power"
			),

	CHAIKIN_OSCILLATOR(
			ChaikinOscillator.KEYWORD, 
			ChaikinOscillator::fromString, 
			"Chaikin Oscillator"
			),

	CHAIKIN_VOLATILITY(
			ChaikinVolatility.KEYWORD, 
			ChaikinVolatility::fromString, 
			"Chaikin Volatility"
			),

	CHANDELIER_EXIT_LONG(
			ChandlelierExitLong.KEYWORD, 
			ChandlelierExitLong::fromString, 
			"Chandelier Exit Long"
			),

	CHANDELIER_EXIT_SHORT(
			ChandlelierExitShort.KEYWORD, 
			ChandlelierExitShort::fromString, 
			"Chandelier Exit Short"
			),

	CHOPPINESS_INDEX(
			ChoppinessIndex.KEYWORD, 
			ChoppinessIndex::fromString, 
			"Choppiness Index"
			),

	CHANDE_MOMENTIUM_OSCILLATOR(
			CMO.KEYWORD, 
			CMO::fromString, 
			"Chande Momentum Oscillator"
			),

	CHAIKIN_MONEY_FLOW(
			ChaikinMoneyFlow.KEYWORD, 
			ChaikinMoneyFlow::fromString, 
			"Chaikin Money Flow"
			),

	COMMODOTY_CHANNEL_INDEX(
			CCI.KEYWORD, 
			CCI::fromString, 
			"Commodity Channel Index"
			),

	CONNORS_RSI(
			ConnorsRSI.KEYWORD, 
			ConnorsRSI::fromString, 
			"Connors' RSI"
			),

	DOUBLE_EXPONTENTIAL_MOVING_AVERAGE(
			DEMA.KEYWORD, 
			DEMA::fromString, 
			"Double Exponential Moving Average"
			),

	EASE_OF_MOVEMENT(
			EMV.KEYWORD, 
			EMV::fromString, 
			"Ease of Movement"
			),

	FORCE_INDEX(
			ForceIndex.KEYWORD, 
			ForceIndex::fromString, 
			"Force Index"
			),

	KELTNER_UPPER_CHANNEL(
			KeltnerUpperChannel.KEYWORD, 
			KeltnerUpperChannel::fromString, 
			"Keltner Upper Channel"
			),

	KELTNER_MIDDLE_CHANNEL(
			KeltnerMiddleChannel.KEYWORD, 
			KeltnerMiddleChannel::fromString, 
			"Keltner Middle Channel"
			),

	KELTNER_LOWER_CHANNEL(
			KeltnerLowerChannel.KEYWORD, 
			KeltnerLowerChannel::fromString, 
			"Keltner Lower Channel"
			),

	MOVEING_AERAGE_CONVERGENCE_DIVERGENCE(
			MACD.KEYWORD, 
			MACD::fromString, 
			"Moving Average Convergence/Divergence"
			),

	MACD_SIGNAL(
			MACDSignal.KEYWORD, 
			MACDSignal::fromString, 
			"Moving Average Convergence/Divergence Signal Line"
			),

	MACD_HISTOGRAM(
			MACDHistogram.KEYWORD, 
			MACDHistogram::fromString, 
			"Moving Average Convergence/Divergence Histogram"
			),

	MASS_INDEX(
			MassIndex.KEYWORD, 
			MassIndex::fromString, 
			"Mass Index"
			),

	MONEY_FLOW_INDEX(
			MoneyFlowIndex.KEYWORD, 
			MoneyFlowIndex::fromString, 
			"Money Flow Index"
			),

	MINUS_DIRECTIONAL_INDEX(
			MinusDI.KEYWORD, 
			MinusDI::fromString, 
			"Negative Directional Index (-DI)"
			),

	NEGATIVE_VOLUME_INDEX(
			NegativeVolumeIndex.KEYWORD, 
			s -> NegativeVolumeIndex.INSTANCE,
			"Negative Volume Index"
			),

	ON_BALANCE_VOLUME(
			OnBalanceVolume.KEYWORD, 
			s -> OnBalanceVolume.INSTANCE,
			"On Balance Volume"
			),

	PLUS_DIRECTIONAL_INDEX(
			PlusDI.KEYWORD, 
			PlusDI::fromString, 
			"Positive Directional Index (+DI)"
			),

	PERCENTAGE_PRICE_OSCILLATOR(
			PPO.KEYWORD, 
			PPO::fromString, 
			"Percentage Price Oscillator"
			),

	PPO_SIGNAL(
			PPOSignal.KEYWORD, 
			PPOSignal::fromString, 
			"Percentage Price Oscillator Signal Line"
			),

	PPO_HISTOGRAM(
			PPOHistogram.KEYWORD, 
			PPOHistogram::fromString, 
			"Percentage Price Oscillator Histogram"
			),

	RELATIVE_STRENGTH_INDEX(
			RSI.KEYWORD, 
			RSI::fromString, 
			"Relative Strength Index"
			),

	SLOPE(
			Slope.KEYWORD, 
			Slope::fromString, 
			"Slope of linear regression line"
			),

	STOCHASTICS_MOMENTUM_INDEX(
			StochasticMomentumIndex.KEYWORD, 
			StochasticMomentumIndex::fromString, 
			"Stochastics Momentum Index"
			),

	STOCHASTICS_OSCILLATOR_K(
			StochasticOscillator.KEYWORD, 
			StochasticOscillator::fromString, 
			"Stochastics Oscillator %K"
			),

	STOCHASTICS_OSCILLATOR_D(
			StochasticOscillatorSignal.KEYWORD, 
			StochasticOscillatorSignal::fromString, 
			"Stochastics Oscillator %D"
			),

	STOCHASTICS_RSI(
			StochasticRSI.KEYWORD, 
			StochasticRSI::fromString, 
			"Stochastics RSI"
			),

	TREND_INTENSITY_INDEX(
			TrendIntensityIndex.KEYWORD, 
			TrendIntensityIndex::fromString, 
			"Trend Intensity Index"
			),

	TRIPLE_EXPONENTIAL_MOVING_AVERAGE(
			TEMA.KEYWORD, 
			TEMA::fromString, 
			"Triple Exponential Moving Average"
			),

	TRIX_LINE(
			TRIX.KEYWORD, 
			TRIX::fromString, 
			"TRIX"
			),

	TRUE_STRENGTH_INDEX(
			TrueStrengthIndex.KEYWORD, 
			TrueStrengthIndex::fromString, 
			"True Strenth Index"
			),

	ULTIMATE_OSCILLATOR(
			UltimateOscillator.KEYWORD, 
			UltimateOscillator::fromString, 
			"Ultimate Oscillator"
			),

	VOLATILITY(
			Volatility.KEYWORD, 
			Volatility::fromString, 
			"Volatility"
			),

	VOLUME(
			Volume.KEYWORD, 
			s -> Volume.INSTANCE,
			"Volume"
			);

	private TechnicalAnalysisIndicator(String keyword, Function<String, NumericExpression> factory, 
					String description) {
				this.keyword = keyword;
				this.factory = factory;
				this.description = description;
			}

	private final String keyword;
	private final Function<String, NumericExpression> factory;
	private final String description;
	
	public String keyword() {
		return keyword;
	}
	
	public Function<String, NumericExpression> factory() {
		return factory;
	}
	
	public String description() {
		return description;
	}
	
	@Override
	public String toString() {
		return keyword;
	}
	
	public NumericExpression expression(String params) {
		return factory.apply(params);
	}
	
	
}
