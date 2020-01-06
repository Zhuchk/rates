package dmitrii.zhuchkov.feature.rates.data.datasource

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import dmitrii.zhuchkov.component.test.RxSchedulersTestRule
import dmitrii.zhuchkov.feature.rates.data.model.RatesModel
import io.reactivex.Single
import org.junit.After
import org.junit.Rule
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class RatesApiDataSourceTest {

	@get:Rule
	val schedulersRule = RxSchedulersTestRule()

	private val api: RatesApi = mock()
	private val dataSource: RatesApiDataSource = RatesApiDataSourceImpl(api)

	@After
	fun doFinally() {
		verifyNoMoreInteractions(api)
	}

	@Test
	fun `WHEN getRates called EXPECT api invokes with same currency code`() {
		val code = "EUR"
		val response = RatesModel(
			base = "EUR",
			date = "",
			rates = mapOf("USD" to 1.0)
		)

		whenever(api.getRates(code)).thenReturn(Single.just(response))

		dataSource.getRates(code)
			.test()
			.assertComplete()

		verify(api).getRates(code)
	}
}