package dmitry.zhuchkov.feature.rates.data.repository

import dmitry.zhuchkov.feature.rates.data.converter.GetLatestResponseConverter
import dmitry.zhuchkov.feature.rates.data.datasource.RatesApiDataSource
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitry.zhuchkov.feature.rates.domain.repository.RatesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class RatesRepositoryImpl @Inject constructor(
	private val dataSource: RatesApiDataSource,
	private val converter: GetLatestResponseConverter
) : RatesRepository {

	override fun get(currencyCode: String): Single<List<CurrencyRate>> =
		dataSource.getRates(currencyCode)
			.map {
				converter.convert(it)
			}
}