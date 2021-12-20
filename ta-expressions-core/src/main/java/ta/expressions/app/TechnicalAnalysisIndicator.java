package ta.expressions.app;

import java.awt.Color;
import java.util.function.Function;

import ta.expressions.common.stats.Slope;
import ta.expressions.core.NumericExpression;
import ta.expressions.indicators.AwesomeOscillator;
import ta.expressions.indicators.AccelerationDeceleration;
import ta.expressions.indicators.BalanceOfPower;
import ta.expressions.indicators.CCI;
import ta.expressions.indicators.CMO;
import ta.expressions.indicators.ConnorsRSI;
import ta.expressions.indicators.EMV;
import ta.expressions.indicators.DEMA;
import ta.expressions.indicators.TEMA;
import ta.expressions.indicators.ForceIndex;
import ta.expressions.indicators.MassIndex;
import ta.expressions.indicators.MoneyFlowIndex;
import ta.expressions.indicators.RSI;
import ta.expressions.indicators.TRIX;
import ta.expressions.indicators.macd.*;
import ta.expressions.indicators.adx.ADX;
import ta.expressions.indicators.bollinger.*;
import ta.expressions.indicators.adx.PlusDI;
import ta.expressions.indicators.adx.MinusDI;
import ta.expressions.indicators.aroon.AroonOscillator;
import ta.expressions.indicators.aroon.AroonUp;
import ta.expressions.indicators.aroon.AroonDown;
import ta.expressions.indicators.chandelier.*;
import ta.expressions.indicators.keltner.*;
import ta.expressions.indicators.stochastics.StochD;
import ta.expressions.indicators.stochastics.StochK;
import ta.expressions.indicators.stochastics.StochRSI;
import ta.expressions.indicators.ultimate.UltimateOscillator;
import ta.expressions.indicators.variables.Volume;
import ta.expressions.indicators.volatility.ATR;
import ta.expressions.indicators.volatility.ChaikinVolatility;
import ta.expressions.indicators.volatility.ChoppinessIndex;
import ta.expressions.indicators.volatility.Volatility;
import ta.expressions.indicators.volume.AccumulationDistribution;
import ta.expressions.indicators.volume.OnBalanceVolume;
import ta.expressions.indicators.volume.NVI;
import ta.expressions.indicators.volume.ChaikinMoneyFlow;
import ta.expressions.indicators.volume.ChaikinOscillator;

public enum TechnicalAnalysisIndicator {
	
	ACCUM_DIST(
			AccumulationDistribution.KEYWORD, 
			s -> AccumulationDistribution.INSTANCE,
			"Accumulation Distribution Line",
			Color.black
			),

	ACCEL_DECEL(
			AccelerationDeceleration.KEYWORD, 
			AccelerationDeceleration::fromString, 
			"Acceleration/Decelation",
			Color.black
			),

	ADX_LINE(
			ADX.KEYWORD, 
			ADX::fromString, 
			"Average Directional Index",
			Color.black
			),

	ATR_LINE(
			ATR.KEYWORD, 
			ATR::fromString, 
			"Average True Range",
			Color.black
			),

	AWESOME(
			AwesomeOscillator.KEYWORD, 
			AwesomeOscillator::fromString, 
			"Awesome Oscillator",
			Color.black
			),
	
	AROON_OSC(
			AroonOscillator.KEYWORD, 
			AroonOscillator::fromString, 
			"Aroon Oscillator",
			Color.black
			),
	
	AROON_UP(
			AroonUp.KEYWORD, 
			AroonUp::fromString, 
			"Aroon Up",
			Color.green
			),
	
	AROON_DOWN(
			AroonDown.KEYWORD, 
			AroonDown::fromString, 
			"Aroon Down",
			Color.red
			),
	
	BB_UPPER(
			BollingerUpperBand.KEYWORD, 
			BollingerUpperBand::fromString, 
			"Bollinger Upper Band",
			Color.black
			),	

	BB_MIDDLE(
			BollingerMiddleBand.KEYWORD, 
			BollingerMiddleBand::fromString, 
			"Bollinger Middle Band",
			Color.black
			),	

	BB_LOWER(
			BollingerLowerBand.KEYWORD, 
			BollingerLowerBand::fromString, 
			"Bollinger Lower Band",
			Color.black
			),	

	BB_PERCENTB(
			BollingerPercentB.KEYWORD, 
			BollingerPercentB::fromString, 
			"Bollinger Bands %B",
			Color.black
			),	

	BB_BANDWIDTH(
			BollingerBandwidth.KEYWORD, 
			BollingerBandwidth::fromString, 
			"Bollinger Bandwidth",
			Color.black
			),	

	BOP(
			BalanceOfPower.KEYWORD, 
			BalanceOfPower::fromString, 
			"Balance of Power",
			Color.black
			),

	CHAIKIN_OSC(
			ChaikinOscillator.KEYWORD, 
			ChaikinOscillator::fromString, 
			"Chaikin Oscillator",
			Color.black
			),

	CHAIKIN_VOLATILITY(
			ChaikinVolatility.KEYWORD, 
			ChaikinVolatility::fromString, 
			"Chaikin Volatility",
			Color.black
			),

	CHANDELIER_LONG(
			ChandlelierExitLong.KEYWORD, 
			ChandlelierExitLong::fromString, 
			"Chandelier Exit Long",
			Color.black
			),

	CHANDELIER_SHORT(
			ChandlelierExitShort.KEYWORD, 
			ChandlelierExitShort::fromString, 
			"Chandelier Exit Short",
			Color.black
			),

	CHOPPINESS_INDEX(
			ChoppinessIndex.KEYWORD, 
			ChoppinessIndex::fromString, 
			"Choppiness Index",
			Color.black
			),

	CM_OSC(
			CMO.KEYWORD, 
			CMO::fromString, 
			"Chande Momentum Oscillator",
			Color.black
			),

	CMF(
			ChaikinMoneyFlow.KEYWORD, 
			ChaikinMoneyFlow::fromString, 
			"Chaikin Money Flow",
			Color.black
			),

	CC_INDEX(
			CCI.KEYWORD, 
			CCI::fromString, 
			"Commodity Channel Index",
			Color.black
			),

	CONNORS_RSI(
			ConnorsRSI.KEYWORD, 
			ConnorsRSI::fromString, 
			"Connors' RSI",
			Color.black
			),

	DEMA_IND(
			DEMA.KEYWORD, 
			DEMA::fromString, 
			"Double Exponential Moving Average",
			Color.black
			),

	EMV_LINE(
			EMV.KEYWORD, 
			EMV::fromString, 
			"Ease of Movement",
			Color.black
			),

	FORCE_INDEX(
			ForceIndex.KEYWORD, 
			ForceIndex::fromString, 
			"Force Index",
			Color.black
			),

	KELTNER_UPPER(
			KeltnerUpperChannel.KEYWORD, 
			KeltnerUpperChannel::fromString, 
			"Keltner Upper Channel",
			Color.black
			),

	KELTNER_MIDDLE(
			KeltnerMiddleChannel.KEYWORD, 
			KeltnerMiddleChannel::fromString, 
			"Keltner Middle Channel",
			Color.black
			),

	KELTNER_LOWER(
			KeltnerLowerChannel.KEYWORD, 
			KeltnerLowerChannel::fromString, 
			"Keltner Lower Channel",
			Color.black
			),

	MACD_LINE(
			MACD.KEYWORD, 
			MACD::fromString, 
			"Moving Average Convergence/Divergence",
			Color.black
			),

	MACD_SIGNAL(
			MACDSignal.KEYWORD, 
			MACDSignal::fromString, 
			"Moving Average Convergence/Divergence Signal Line",
			Color.red
			),

	MACD_HISTOGRAM(
			MACDHistogram.KEYWORD, 
			MACDHistogram::fromString, 
			"Moving Average Convergence/Divergence Histogram",
			Color.gray
			),

	MASS_INDEX(
			MassIndex.KEYWORD, 
			MassIndex::fromString, 
			"Mass Index",
			Color.black
			),

	MFI(
			MoneyFlowIndex.KEYWORD, 
			MoneyFlowIndex::fromString, 
			"Money Flow Index",
			Color.black
			),

	MINUS_DI(
			MinusDI.KEYWORD, 
			MinusDI::fromString, 
			"Negative Directional Index (-DI)",
			Color.red
			),

	NVI_LINE(
			NVI.KEYWORD, 
			s -> NVI.INSTANCE,
			"Negative Volume Index",
			Color.black
			),

	PLUS_DI(
			PlusDI.KEYWORD, 
			PlusDI::fromString, 
			"Positive Directional Index (+DI)",
			Color.green
			),

	PPO_LINE(
			PPO.KEYWORD, 
			PPO::fromString, 
			"Percentage Price Oscillator",
			Color.black
			),

	PPO_SIGNAL(
			PPOSignal.KEYWORD, 
			PPOSignal::fromString, 
			"Percentage Price Oscillator Signal Line",
			Color.red
			),

	PPO_HISTOGRAM(
			PPOHistogram.KEYWORD, 
			PPOHistogram::fromString, 
			"Percentage Price Oscillator Histogram",
			Color.gray
			),

	RS_INDEX(
			RSI.KEYWORD, 
			RSI::fromString, 
			"Relative Strength Index",
			Color.black
			),

	SLOPE(
			Slope.KEYWORD, 
			Slope::fromString, 
			"Slope of linear regression line",
			Color.black
			),

	STOCH_K(
			StochK.KEYWORD, 
			StochK::fromString, 
			"Stochastics Oscillator %K",
			Color.black
			),

	STOCH_D(
			StochD.KEYWORD, 
			StochD::fromString, 
			"Stochastics Oscillator %D",
			Color.red
			),

	STOCH_RSI(
			StochRSI.KEYWORD, 
			StochRSI::fromString, 
			"Stochastics RSI",
			Color.black
			),

	TEMA_IND(
			TEMA.KEYWORD, 
			TEMA::fromString, 
			"Triple Exponential Moving Average",
			Color.black
			),

	TRIX_LINE(
			TRIX.KEYWORD, 
			TRIX::fromString, 
			"TRIX",
			Color.black
			),

	ULTIMATE(
			UltimateOscillator.KEYWORD, 
			UltimateOscillator::fromString, 
			"Ultimate Oscillator",
			Color.black
			),

	VOLATILITY(
			Volatility.KEYWORD, 
			Volatility::fromString, 
			"Volatility",
			Color.black
			),

	VOLUME(
			Volume.KEYWORD, 
			s -> Volume.INSTANCE,
			"Volume",
			Color.gray
			),

	OBV(
			OnBalanceVolume.KEYWORD, 
			s -> OnBalanceVolume.INSTANCE,
			"On Balance Volume",
			Color.black
			);

	private TechnicalAnalysisIndicator(String keyword, Function<String, NumericExpression> factory, 
					String description, Color defaultColor) {
				this.keyword = keyword;
				this.factory = factory;
				this.description = description;
				this.defaultColor = defaultColor;
			}

	private final String keyword;
	private final Function<String, NumericExpression> factory;
	private final String description;
	private final Color defaultColor;
	
	public String keyword() {
		return keyword;
	}
	
	public Function<String, NumericExpression> factory() {
		return factory;
	}
	
	public String description() {
		return description;
	}
	
	public Color defaultColor() {
		return defaultColor;
	}

	@Override
	public String toString() {
		return keyword;
	}
	
	public NumericExpression expression(String params) {
		return factory.apply(params);
	}
	
	
}
