package dmitrii.zhuchkov.feature.rates.domain.usecase

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.RatesRepository
import io.reactivex.Single
import org.joda.money.CurrencyUnit
import java.math.BigDecimal
import java.util.*
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

	operator fun invoke(currencyCode: String, amount: BigDecimal): Single<List<CurrencyRate>> =
		repository.get(currencyCode)
			.map {
				listOf(createBase(currencyCode, amount)) + it.addFullName()
			}

	private fun List<CurrencyRate>.addFullName() : List<CurrencyRate> =
		this.map { currencyRate ->
			currencyRate.copy(
				fullName = getDisplayedName(currencyRate.code)
			)
		}

	private fun createBase(currencyCode: String, amount: BigDecimal): CurrencyRate =
		CurrencyRate(
			code = currencyCode,
			value = amount,
			rate = BigDecimal.valueOf(RATE_FOR_BASE_CURRENCY),
			fullName = getDisplayedName(currencyCode)
		)

	private fun getDisplayedName(code: String): String =
		try {
			CurrencyUnit.of(code).toCurrency().getDisplayName(Locale.getDefault())
		} catch (exception: Exception) {
			""
		}
}