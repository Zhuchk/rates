package dmitrii.zhuchkov.feature.rates.domain

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.usecase.CalculateRatesUseCase
import junit.framework.Assert.*
import org.junit.Test
import java.math.BigDecimal

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class CalculateRatesUseCaseTest {

	private val useCase = CalculateRatesUseCase()

	@Test
	fun `WHEN only value changes EXPECT calculated correct values`() {
		val oldBase = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(1.00), value = BigDecimal.valueOf(10))

		val newBase = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(1.00), value = BigDecimal.valueOf(20))

		val rates = listOf(
			CurrencyRate(code ="RUB", rate = BigDecimal.valueOf(0.5), value = BigDecimal.valueOf(20)),
			CurrencyRate(code ="USD", rate = BigDecimal.valueOf(2), value = BigDecimal.valueOf(5))
		)
		val expectedResult =listOf(
			CurrencyRate(code ="RUB", rate = BigDecimal.valueOf(0.5), value = BigDecimal.valueOf(40)),
			CurrencyRate(code ="USD", rate = BigDecimal.valueOf(2), value = BigDecimal.valueOf(10))
		)

		val actualResult = useCase.invoke(rates, oldBase, newBase)

		assertEquals(expectedResult, actualResult)
	}

	@Test
	fun `WHEN rate increase EXPECT calculated correct values`() {
		val oldBase = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(10))

		val newBase = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(7), value = BigDecimal.valueOf(10))

		val rates = listOf(
			CurrencyRate(code ="RUB", rate = BigDecimal.valueOf(0.5), value = BigDecimal.valueOf(20)),
			CurrencyRate(code ="USD", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(5))
		)
		val expectedResult =listOf(
			CurrencyRate(code ="RUB", rate = BigDecimal.valueOf(3.5), value = BigDecimal.valueOf(35)),
			CurrencyRate(code ="USD", rate = BigDecimal.valueOf(7), value = BigDecimal.valueOf(70))
		)

		val actualResult = useCase.invoke(rates, oldBase, newBase)

		assertEquals(expectedResult, actualResult)
	}

	@Test
	fun `WHEN rate decrease EXPECT calculated correct values`() {
		val oldBase = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(10))

		val newBase = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(0.5), value = BigDecimal.valueOf(10))

		val rates = listOf(
			CurrencyRate(code ="RUB", rate = BigDecimal.valueOf(0.5), value = BigDecimal.valueOf(20)),
			CurrencyRate(code ="USD", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(5))
		)
		val expectedResult =listOf(
			CurrencyRate(code ="RUB", rate = BigDecimal.valueOf(0.25), value = BigDecimal.valueOf(40)),
			CurrencyRate(code ="USD", rate = BigDecimal.valueOf(0.5), value = BigDecimal.valueOf(20))
		)

		val actualResult = useCase.invoke(rates, oldBase, newBase)

		assertEquals(expectedResult, actualResult)
	}

	@Test
	fun `WHEN rate and value changes EXPECT calculated correct values`() {
		val oldBase = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(10))

		val newBase = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(0.5), value = BigDecimal.valueOf(5))

		val rates = listOf(
			CurrencyRate(code ="RUB", rate = BigDecimal.valueOf(0.5), value = BigDecimal.valueOf(20)),
			CurrencyRate(code ="USD", rate = BigDecimal.valueOf(2.0), value = BigDecimal.valueOf(5))
		)
		val expectedResult =listOf(
			CurrencyRate(code ="RUB", rate = BigDecimal.valueOf(0.25), value = BigDecimal.valueOf(20)),
			CurrencyRate(code ="USD", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(5))
		)

		val actualResult = useCase.invoke(rates, oldBase, newBase)

		assertEquals(expectedResult, actualResult)
	}
}