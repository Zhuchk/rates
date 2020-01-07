package dmitrii.zhuchkov.feature.rates.data.converter

import dmitrii.zhuchkov.feature.rates.data.model.RatesModel
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import org.joda.money.CurrencyUnit
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class RatesConverter @Inject constructor() {

	fun convert(response: RatesModel): List<CurrencyRate> =
		response.rates
			.map { (name, value) ->
				CurrencyRate(
					code = name,
					value = BigDecimal.valueOf(value),
					rate = BigDecimal.valueOf(value),
					fullName = getDisplayedName(name)
				)
			}

	private fun getDisplayedName(code: String): String =
		try {
			CurrencyUnit.of(code).toCurrency().getDisplayName(Locale.getDefault())
		} catch (exception: Exception) {
			""
		}
}