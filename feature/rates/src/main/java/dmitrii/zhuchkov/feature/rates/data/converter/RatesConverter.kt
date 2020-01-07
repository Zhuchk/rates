package dmitrii.zhuchkov.feature.rates.data.converter

import dmitrii.zhuchkov.feature.rates.data.model.RatesModel
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import java.math.BigDecimal
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
					fullName = ""
				)
			}
}