package dmitrii.zhuchkov.feature.rates.data.datasource

import dmitrii.zhuchkov.feature.rates.data.model.RatesModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
interface RatesApi {

	@GET("latest")
	fun getRates(@Query("base") currencyCode: String): Single<RatesModel>
}