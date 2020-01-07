package dmitrii.zhuchkov.feature.rates.presentation

import com.nhaarman.mockito_kotlin.*
import dmitrii.zhuchkov.component.test.RxSchedulersTestRule
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.usecase.CalculateRatesUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.GetBaseCurrencyUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.GetRatesUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.SetBaseCurrencyUseCase
import io.reactivex.Single
import org.junit.After
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal

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
	private val calculateRatesUseCase = CalculateRatesUseCase()
	private val viewState: RatesView = mock()

	private val presenter = RatesPresenter(
		getRatesUseCase,
		getBaseCurrencyUseCase,
		setBaseCurrencyUseCase,
		calculateRatesUseCase
	)

	@Test
	fun `WHEN current rate doesn't changes EXPECT base currency remains the same`() {
		val currencyRate = BigDecimal.valueOf(10.0)
		val currencyValue = BigDecimal.valueOf(10.0)
		val currentRate = CurrencyRate(code = "EUR", value = currencyValue, rate = currencyRate)
		val newRate = currentRate.value

		whenever(getBaseCurrencyUseCase()).thenReturn(currentRate)

		presenter.onCurrentRateChanged(newRate.toString())

		verify(setBaseCurrencyUseCase, never())(any())
	}

	@Test
	fun `WHEN current rate changes EXPECT setBaseCurrencyUseCase invoked with correct value`() {
		val currencyRate = BigDecimal.valueOf(10.0)
		val currencyValue = BigDecimal.valueOf(10.0)
		val newCurrencyValue = BigDecimal.valueOf(15.0)

		val currentRate = CurrencyRate(code = "EUR", value = currencyValue, rate = currencyRate)
		val newRate = CurrencyRate(code = "EUR", value = newCurrencyValue, rate = currencyRate)


		whenever(getBaseCurrencyUseCase()).thenReturn(currentRate)

		presenter.onCurrentRateChanged(newCurrencyValue.toPlainString())

		verify(setBaseCurrencyUseCase)(newRate)
	}

	@Test
	fun `WHEN current rate changed EXPECT view show recalculated list`() {
		val newValue = "20"
		val baseCurrency = CurrencyRate(code = "EUR", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(10))
		val ratesList = listOf(CurrencyRate(code = "USD", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(10)))
		val expectedList = listOf(CurrencyRate(code = "USD", rate = BigDecimal.valueOf(1), value = BigDecimal.valueOf(20)))

		whenever(getBaseCurrencyUseCase()).thenReturn(
			baseCurrency,
			baseCurrency.copy(value = BigDecimal.valueOf(newValue.toDouble()))
		)
		whenever(getRatesUseCase(baseCurrency.code, baseCurrency.value)).thenReturn(Single.just(ratesList))

		presenter.viewState = viewState
		val field = RatesPresenter::class.java.getDeclaredField("ratesList")
		field.isAccessible = true
		field.set(presenter, ratesList)

		presenter.onCurrentRateChanged(newValue)

		verify(viewState).showRates(expectedList)
	}
}