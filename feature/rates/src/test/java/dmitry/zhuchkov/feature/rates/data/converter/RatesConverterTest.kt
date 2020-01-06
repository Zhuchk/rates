package dmitry.zhuchkov.feature.rates.data.converter

import dmitry.zhuchkov.feature.rates.data.model.RatesModel
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class RatesConverterTest {

	private val converter = RatesConverter()

	@Test
	fun `WHEN convert RatesModel EXPECT returns List of CurrencyRate `() {
		val ratesModel = RatesModel(
			base = "RUB",
			date = "",
			rates = mapOf(
				"EUR" to 11.0,
				"USD" to 15.0
			)
		)

		val expectedResult = listOf(
			CurrencyRate(
				code = "EUR",
				value = 11.0,
				rate = 11.0,
				fullName = "Euro"
			),
			CurrencyRate(
				code = "USD",
				value = 15.0,
				rate = 15.0,
				fullName = "US Dollar"
			)
		)

		val actualResult = converter.convert(ratesModel)

		assertEquals(expectedResult, actualResult)
	}

	@Test
	fun `WHEN code is incorrect EXPECT empty fullName returns`() {
		val ratesModel = RatesModel(
			base = "RUB",
			date = "",
			rates = mapOf("UNKNOWN" to 19.0)
		)

		val expectedResult = listOf(
			CurrencyRate(
				code = "UNKNOWN",
				value = 19.0,
				rate = 19.0,
				fullName = ""
			)
		)

		val actualResult = converter.convert(ratesModel)

		assertEquals(expectedResult, actualResult)
	}
}