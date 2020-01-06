package dmitrii.zhuchkov.feature.rates.presentation

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import dmitrii.zhuchkov.component.test.RxSchedulersTestRule
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.usecase.CalculateRatesUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.GetBaseCurrencyUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.GetRatesUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.SetBaseCurrencyUseCase
import org.junit.After
import org.junit.Rule
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class RatesPresenterTest {

	@get:Rule
	val schedulersRule = RxSchedulersTestRule()

	private val getRatesUseCase: GetRatesUseCase = mock()
	private val getBaseCurrencyUseCase: GetBaseCurrencyUseCase = mock()
	private val setBaseCurrencyUseCase: SetBaseCurrencyUseCase = mock()
	private val calculateRatesUseCase: CalculateRatesUseCase = mock()

	private val presenter = RatesPresenter(
		getRatesUseCase,
		getBaseCurrencyUseCase,
		setBaseCurrencyUseCase,
		calculateRatesUseCase
	)

	@After
	fun doFinally() {
		verifyNoMoreInteractions(
			getRatesUseCase,
			getBaseCurrencyUseCase,
			setBaseCurrencyUseCase,
			calculateRatesUseCase
		)
	}

	@Test
	fun `WHEN current rate doesn't changes EXPECT base currency remains the same`() {
		val currentRate = CurrencyRate(code = "EUR", value = 10.0, rate = 10.0)
		val newRate = currentRate.value

		whenever(getBaseCurrencyUseCase()).thenReturn(currentRate)

		presenter.onCurrentRateChanged(newRate.toString())

		verify(getBaseCurrencyUseCase)()
	}

	@Test
	fun `WHEN current rate changes EXPECT list items changes on view`() {
		val currentRate = CurrencyRate(code = "EUR", value = 1.0, rate = 1.0)
		val newRate = CurrencyRate(code = "EUR", value = 1.00, rate = 1.0)


		whenever(getBaseCurrencyUseCase()).thenReturn(currentRate)

		presenter.onCurrentRateChanged(newRate.toString())

		verify(getBaseCurrencyUseCase)()
	}
}