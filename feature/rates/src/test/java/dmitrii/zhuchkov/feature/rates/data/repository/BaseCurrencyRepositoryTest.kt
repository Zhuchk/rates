package dmitrii.zhuchkov.feature.rates.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import dmitrii.zhuchkov.feature.rates.data.datasource.BaseCurrencyDataSource
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.BaseCurrencyRepository
import junit.framework.Assert.*
import org.junit.After
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class BaseCurrencyRepositoryTest {

	private val dataSource: BaseCurrencyDataSource = mock()

	private val repository: BaseCurrencyRepository = BaseCurrencyRepositoryImpl(dataSource)

	@After
	fun doFinally() {
		verifyNoMoreInteractions(dataSource)
	}

	@Test
	fun `WHEN called get EXPECT dataSource returns value`() {
		val rate: CurrencyRate = mock()

		whenever(dataSource.get()).thenReturn(rate)

		val result = repository.get()

		assertEquals(rate, result)
		verify(dataSource).get()
	}

	@Test
	fun `WHEN set called EXPECT dataSources invokes with same CurrencyRate`() {
		val rate: CurrencyRate = mock()

		repository.set(rate)

		verify(dataSource).set(rate)
	}
}