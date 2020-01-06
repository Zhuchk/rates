package dmitry.zhuchkov.feature.rates.data.converter

import dmitry.zhuchkov.feature.rates.data.model.RatesModel
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import org.joda.money.CurrencyUnit
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
					value = value,
					rate = value,
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