package dmitry.zhuchkov.feature.rates.data.datasource

import dmitry.zhuchkov.feature.rates.data.model.RatesModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
interface RatesApiDataSource {

	fun getRates(currencyCode: String): Single<RatesModel>
}

class RatesApiDataSourceImpl @Inject constructor(
	private val api: RatesApi
) : RatesApiDataSource {

	override fun getRates(currencyCode: String): Single<RatesModel> =
		api.getRates(currencyCode)
			.subscribeOn(Schedulers.io())
}