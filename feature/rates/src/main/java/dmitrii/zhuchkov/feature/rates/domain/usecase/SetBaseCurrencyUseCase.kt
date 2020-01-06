package dmitrii.zhuchkov.feature.rates.domain.usecase

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.repository.BaseCurrencyRepository
import javax.inject.Inject

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class SetBaseCurrencyUseCase @Inject constructor(
	private val repository: BaseCurrencyRepository
) {

	operator fun invoke(baseCurrency: CurrencyRate) {
		repository.set(baseCurrency)
	}
}