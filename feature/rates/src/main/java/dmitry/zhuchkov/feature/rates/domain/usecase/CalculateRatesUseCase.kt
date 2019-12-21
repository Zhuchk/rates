package dmitry.zhuchkov.feature.rates.domain.usecase

import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import javax.inject.Inject

/**
 * Created by Dmitrii on 21.12.2019
 *
 */
class CalculateRatesUseCase @Inject constructor() {

	operator fun invoke(
		rates: List<CurrencyRate>,
		oldBase: CurrencyRate,
		newBase: CurrencyRate
	): List<CurrencyRate> {
		val multiplier = oldBase.rate / newBase.rate

		return rates
			.map { currencyRate ->
				val rate = currencyRate.rate / multiplier

				currencyRate.copy(
					rate = rate,
					value = rate * newBase.value
				)
			}
	}
}