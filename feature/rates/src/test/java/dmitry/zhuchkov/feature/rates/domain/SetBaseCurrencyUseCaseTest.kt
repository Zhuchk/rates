package dmitry.zhuchkov.feature.rates.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitry.zhuchkov.feature.rates.domain.repository.BaseCurrencyRepository
import dmitry.zhuchkov.feature.rates.domain.usecase.SetBaseCurrencyUseCase
import org.junit.After
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class SetBaseCurrencyUseCaseTest {

	private val repository: BaseCurrencyRepository = mock()
	private val useCase = SetBaseCurrencyUseCase(repository)

	@After
	fun doFinally() {
		verifyNoMoreInteractions(repository)
	}

	@Test
	fun `WHEN useCase invokes EXPECT repository set baseCurrency`() {
		val baseCurrency: CurrencyRate = mock()
		useCase(baseCurrency)

		verify(repository).set(baseCurrency)
	}
}