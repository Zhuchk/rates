package dmitrii.zhuchkov.feature.rates.data.repository

import dmitrii.zhuchkov.feature.rates.data.datasource.BaseCurrencyDataSource
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.BaseCurrencyRepository
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class BaseCurrencyRepositoryImpl @Inject constructor(
	private val dataSource: BaseCurrencyDataSource
) : BaseCurrencyRepository {

	override fun get(): CurrencyRate? =
		dataSource.get()

	override fun set(baseCurrency: CurrencyRate) {
		dataSource.set(baseCurrency)
	}
}