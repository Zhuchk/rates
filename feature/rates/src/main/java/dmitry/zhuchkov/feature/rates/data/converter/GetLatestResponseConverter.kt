package dmitry.zhuchkov.feature.rates.data.converter

import dmitry.zhuchkov.feature.rates.data.model.RatesModel
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class GetLatestResponseConverter @Inject constructor() {

    fun convert(response: RatesModel): List<CurrencyRate> =
        response.rates
            .map { (name, value) ->
                CurrencyRate(
                    name = name,
                    value = value,
                    rate = value
                )
            }
}