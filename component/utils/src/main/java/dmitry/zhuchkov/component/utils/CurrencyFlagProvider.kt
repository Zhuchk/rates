package dmitry.zhuchkov.component.utils

/**
 * Created by Dmitrii on 22.12.2019
 *
 */
object CurrencyFlagProvider {

	fun getFlagByCode(code: String): String =
		when (code) {
			"EUR" -> "https://github.com/Zhuchk/rates/blob/master/united-states.png?raw=true"
			"USD" -> "https://github.com/Zhuchk/rates/blob/master/united-states.png"
			"RUB" -> "https://github.com/Zhuchk/rates/blob/master/united-states.png"

			else  -> "https://github.com/Zhuchk/rates/blob/master/united-states.png"
		}
}