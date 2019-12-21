package dmitry.zhuchkov.feature.rates.data.model

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
data class RatesModel(
	val base: String,
	val date: String,
	val rates: Map<String, Double>
)