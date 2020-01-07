package dmitrii.zhuchkov.feature.rates.domain.usecase

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import java.math.RoundingMode
import javax.inject.Inject

/**
 * Created by Dmitrii on 21.12.2019
 *
 */
class CalculateRatesUseCase @Inject constructor() {

	operator fun invoke(rates: List<CurrencyRate>, oldBase: CurrencyRate, newBase: CurrencyRate): List<CurrencyRate> =
		rates.map { currencyRate ->
			val multiplier = oldBase.rate.divide(newBase.rate, 6, RoundingMode.HALF_EVEN)

			val rate = currencyRate.rate.divide(multiplier, 2, RoundingMode.HALF_EVEN)

			val newValue = if (oldBase.rate < newBase.rate) {
				newBase.value.multiply(rate).stripTrailingZeros()
			} else {
				newBase.value.divide(rate, 2, RoundingMode.HALF_EVEN).stripTrailingZeros()
			}

			currencyRate.copy(
				rate = rate.stripTrailingZeros(),
				value = newValue.toPlainString().toBigDecimal() //newBase.value.multiply(rate).stripTrailingZeros().toPlainString().toBigDecimal()
			)
		}
}