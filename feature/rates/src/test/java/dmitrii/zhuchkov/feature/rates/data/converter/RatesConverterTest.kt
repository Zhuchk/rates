package dmitrii.zhuchkov.feature.rates.data.converter

import dmitrii.zhuchkov.feature.rates.data.model.RatesModel
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

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
				value = BigDecimal.valueOf(11.0),
				rate = BigDecimal.valueOf(11.0),
				fullName = ""
			),
			CurrencyRate(
				code = "USD",
				value = BigDecimal.valueOf(15.0),
				rate = BigDecimal.valueOf(15.0),
				fullName = ""
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
				value = BigDecimal.valueOf(19.0),
				rate = BigDecimal.valueOf(19.0),
				fullName = ""
			)
		)

		val actualResult = converter.convert(ratesModel)

		assertEquals(expectedResult, actualResult)
	}
}