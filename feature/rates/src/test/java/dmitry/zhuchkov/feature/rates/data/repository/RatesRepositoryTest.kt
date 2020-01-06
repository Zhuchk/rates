package dmitry.zhuchkov.feature.rates.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import dmitry.zhuchkov.component.test.RxSchedulersTestRule
import dmitry.zhuchkov.feature.rates.data.converter.RatesConverter
import dmitry.zhuchkov.feature.rates.data.datasource.RatesApiDataSource
import dmitry.zhuchkov.feature.rates.data.model.RatesModel
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitry.zhuchkov.feature.rates.domain.repository.RatesRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Rule
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class RatesRepositoryTest {

	@get:Rule
	val schedulersRule = RxSchedulersTestRule()

	private val dataSource: RatesApiDataSource = mock()
	private val converter: RatesConverter = mock()
	private val repository: RatesRepository = RatesRepositoryImpl(dataSource, converter)

	@After
	fun doFinally() {
		verifyNoMoreInteractions(dataSource, converter)
	}

	@Test
	fun `WHEN called get EXPECT CurrencyRate list returns`() {
		val code = "EUR"
		val response = RatesModel(
			base = "EUR",
			date = "",
			rates = mapOf("USD" to 1.0)
		)
		val convertedModel = listOf(
			CurrencyRate(
				code = "USD",
				value = 1.0,
				rate = 1.0,
				fullName = ""
			)
		)

		whenever(dataSource.getRates(code)).thenReturn(Single.just(response))
		whenever(converter.convert(response)).thenReturn(convertedModel)

		repository.get(code)
			.test()
			.assertValue(convertedModel)
			.assertComplete()

		verify(dataSource).getRates(code)
		verify(converter).convert(response)
	}
}