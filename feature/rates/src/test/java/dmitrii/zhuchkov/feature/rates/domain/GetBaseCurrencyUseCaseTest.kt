package dmitrii.zhuchkov.feature.rates.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.BaseCurrencyRepository
import dmitrii.zhuchkov.feature.rates.domain.usecase.GetBaseCurrencyUseCase
import junit.framework.Assert.*
import org.junit.After
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class GetBaseCurrencyUseCaseTest {

	private val repository: BaseCurrencyRepository = mock()
	private val useCase = GetBaseCurrencyUseCase(repository)

	@After
	fun doFinally() {
		verifyNoMoreInteractions(repository)
	}

	@Test
	fun `WHEN dataSource returns null EXPECT default currency returns`() {
		val expectedResult = CurrencyRate(code = "EUR", value = 1.0)
		whenever(repository.get()).thenReturn(null)

		val actualResult = useCase.invoke()

		assertEquals(expectedResult, actualResult)

		verify(repository).get()
	}

	@Test
	fun `WHEN invoke calls EXPECT data from repository returns`() {
		val expectedResult = CurrencyRate(
			code = "EUR",
			value = 1.5
		)
		whenever(repository.get()).thenReturn(expectedResult)

		val actualResult = useCase.invoke()

		assertEquals(expectedResult, actualResult)

		verify(repository).get()
	}
}