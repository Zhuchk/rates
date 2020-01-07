package dmitrii.zhuchkov.feature.rates.domain.entity

import java.math.BigDecimal

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
data class CurrencyRate(
    val code: String,
    val value: BigDecimal,
    val rate: BigDecimal,
    val fullName: String = ""
)