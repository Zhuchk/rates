package dmitrii.zhuchkov.feature.rates.domain

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.usecase.CalculateRatesUseCase
import junit.framework.Assert.*
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class CalculateRatesUseCaseTest {

	private val useCase = CalculateRatesUseCase()

	@Test
	fun `WHEN only value changes EXPECT calculated correct values`() {
		val oldBase = CurrencyRate(code = "EUR", rate = 1.0, value = 10.0)

		val newBase = CurrencyRate(code = "EUR", rate = 1.0, value = 20.0)

		val rates = listOf(
			CurrencyRate(code ="RUB", rate = 0.5, value = 20.0),
			CurrencyRate(code ="USD", rate = 2.0, value = 5.0)
		)
		val expectedResult =listOf(
			CurrencyRate(code ="RUB", rate = 0.5, value = 40.0),
			CurrencyRate(code ="USD", rate = 2.0, value = 10.0)
		)

		val actualResult = useCase.invoke(rates, oldBase, newBase)

		assertEquals(expectedResult, actualResult)
	}

	@Test
	fun `WHEN rate changes EXPECT calculated correct values`() {
		val oldBase = CurrencyRate(code = "EUR", rate = 1.0, value = 10.0)

		val newBase = CurrencyRate(code = "EUR", rate = 2.0, value = 10.0)

		val rates = listOf(
			CurrencyRate(code ="RUB", rate = 0.5, value = 20.0),
			CurrencyRate(code ="USD", rate = 2.0, value = 5.0)
		)
		val expectedResult =listOf(
			CurrencyRate(code ="RUB", rate = 0.25, value = 40.0),
			CurrencyRate(code ="USD", rate = 1.0, value = 10.0)
		)

		val actualResult = useCase.invoke(rates, oldBase, newBase)

		assertEquals(expectedResult, actualResult)
	}

	@Test
	fun `WHEN rate and value changes EXPECT calculated correct values`() {
		val oldBase = CurrencyRate(code = "EUR", rate = 1.0, value = 10.0)

		val newBase = CurrencyRate(code = "EUR", rate = 2.0, value = 5.0)

		val rates = listOf(
			CurrencyRate(code ="RUB", rate = 0.5, value = 20.0),
			CurrencyRate(code ="USD", rate = 2.0, value = 5.0)
		)
		val expectedResult =listOf(
			CurrencyRate(code ="RUB", rate = 0.25, value = 20.0),
			CurrencyRate(code ="USD", rate = 1.0, value = 5.0)
		)

		val actualResult = useCase.invoke(rates, oldBase, newBase)

		assertEquals(expectedResult, actualResult)
	}
}