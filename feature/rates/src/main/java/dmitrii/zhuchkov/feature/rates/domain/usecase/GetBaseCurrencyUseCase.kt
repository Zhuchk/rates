package dmitrii.zhuchkov.feature.rates.domain.usecase

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.BaseCurrencyRepository
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class GetBaseCurrencyUseCase @Inject constructor(
	private val repository: BaseCurrencyRepository
) {

	private companion object {
		const val BASE_CURRENCY_CODE = "EUR"
		const val BASE_RATE = 1.0
	}

	operator fun invoke(): CurrencyRate =
		repository.get() ?: getDefaultCurrency()

	private fun getDefaultCurrency(): CurrencyRate =
		CurrencyRate(
			code = BASE_CURRENCY_CODE,
			value = BASE_RATE
		)
}