package dmitrii.zhuchkov.feature.rates.domain.entity

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
data class CurrencyRate(
    val code: String,
    val value: Double,
    val rate: Double = 1.0,
    val fullName: String = ""
)