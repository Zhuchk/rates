package dmitrii.zhuchkov.feature.rates.data.repository

import dmitrii.zhuchkov.feature.rates.data.converter.RatesConverter
import dmitrii.zhuchkov.feature.rates.data.datasource.RatesApiDataSource
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.RatesRepository
import io.reactivex.Single
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class RatesRepositoryImpl @Inject constructor(
	private val dataSource: RatesApiDataSource,
	private val converter: RatesConverter
) : RatesRepository {

	override fun get(currencyCode: String): Single<List<CurrencyRate>> =
		dataSource.getRates(currencyCode)
			.map {
				converter.convert(it)
			}
}