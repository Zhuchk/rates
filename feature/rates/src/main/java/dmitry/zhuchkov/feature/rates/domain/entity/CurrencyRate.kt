package dmitry.zhuchkov.feature.rates.domain.entity

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
data class CurrencyRate(
    val name: String,
    val value: Double,
    val rate: Double = 1.0
)