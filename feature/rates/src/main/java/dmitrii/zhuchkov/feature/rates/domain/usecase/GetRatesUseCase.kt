package dmitrii.zhuchkov.feature.rates.domain.usecase

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.RatesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class GetRatesUseCase @Inject constructor(
	private val repository: RatesRepository
) {

	private companion object {
		const val RATE_FOR_BASE_CURRENCY = 1.0
	}

	operator fun invoke(currencyCode: String, amount: Double): Single<List<CurrencyRate>> =
		repository.get(currencyCode)
			.map {
				listOf(createBase(currencyCode, amount)) + it
			}

	private fun createBase(currencyCode: String, amount: Double): CurrencyRate =
		CurrencyRate(
			code = currencyCode,
			value = amount,
			rate = RATE_FOR_BASE_CURRENCY
		)
}