package dmitry.zhuchkov.feature.rates.domain.usecase

import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import javax.inject.Inject

/**
 * Created by Dmitrii on 21.12.2019
 *
 */
class CalculateRatesUseCase @Inject constructor() {

	operator fun invoke(rates: List<CurrencyRate>, oldBase: CurrencyRate, newBase: CurrencyRate): List<CurrencyRate> =
		rates.map { currencyRate ->
			val multiplier = oldBase.rate / newBase.rate

			val rate = currencyRate.rate * multiplier

			currencyRate.copy(
				rate = rate,
				value = newBase.value / rate
			)
		}
}