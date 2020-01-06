package dmitrii.zhuchkov.component.utils

/**
 * Created by Dmitrii on 22.12.2019
 *
 */
object CurrencyFlagProvider {

	fun getFlagByCode(code: String): String =
		when (code) {
			"EUR" -> "https://github.com/Zhuchk/rates/blob/master/flags/european-union.png?raw=true"
			"USD" -> "https://github.com/Zhuchk/rates/blob/master/flags/united-states.png?raw=true"
			"RUB" -> "https://github.com/Zhuchk/rates/blob/master/flags/russia.png?raw=true"

			else  -> "https://github.com/Zhuchk/rates/blob/master/flags/coin.png?raw=true"
		}
}