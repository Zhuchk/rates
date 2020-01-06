package dmitrii.zhuchkov.feature.rates.data.datasource

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
interface BaseCurrencyDataSource {

	fun get(): CurrencyRate?

	fun set(baseCurrency: CurrencyRate)
}

class BaseCurrencyDataSourceImpl @Inject constructor() : BaseCurrencyDataSource {

	private var baseCurrency: CurrencyRate? = null

	override fun get(): CurrencyRate? =
		baseCurrency

	override fun set(baseCurrency: CurrencyRate) {
		this.baseCurrency = baseCurrency
	}
}