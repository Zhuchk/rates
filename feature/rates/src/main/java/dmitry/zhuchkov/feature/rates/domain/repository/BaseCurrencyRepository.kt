package dmitry.zhuchkov.feature.rates.domain.repository

import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
interface BaseCurrencyRepository {

    fun get(): CurrencyRate?

    fun set(baseCurrency: CurrencyRate)
}