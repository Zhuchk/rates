package dmitrii.zhuchkov.feature.rates.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import dmitrii.zhuchkov.component.test.RxSchedulersTestRule
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.RatesRepository
import dmitrii.zhuchkov.feature.rates.domain.usecase.GetRatesUseCase
import io.reactivex.Single
import org.junit.After
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class GetRatesUseCaseTest {

	@get:Rule
	val schedulersRule = RxSchedulersTestRule()

	private val repository: RatesRepository = mock()
	private val useCase = GetRatesUseCase(repository)

	@After
	fun doFinally() {
		verifyNoMoreInteractions(repository)
	}

	@Test
	fun `WHEN useCase called EXPECT return rates with correct base item`() {
		val code = "EUR"
		val amount = BigDecimal.valueOf(10.0)

		val currencyList = listOf(
			CurrencyRate(code = "USD", value = BigDecimal.valueOf(1.2), rate = BigDecimal.valueOf(1.2)),
			CurrencyRate(code = "RUB", value = BigDecimal.valueOf(0.25), rate = BigDecimal.valueOf(1.2))
		)

		val expectedResult = listOf(
			CurrencyRate(code = code, value = amount, rate = BigDecimal.valueOf(1.0))
		) + currencyList

		whenever(repository.get(code)).thenReturn(Single.just(currencyList))

		useCase(code, amount)
			.test()
			.assertValue(expectedResult)

		verify(repository).get(code)
	}
}