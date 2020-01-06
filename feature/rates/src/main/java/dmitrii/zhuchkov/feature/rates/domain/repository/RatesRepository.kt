package dmitrii.zhuchkov.feature.rates.domain.repository

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import io.reactivex.Single

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
interface RatesRepository {

	fun get(currencyCode: String): Single<List<CurrencyRate>>
}