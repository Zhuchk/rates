package dmitry.zhuchkov.feature.rates.presentation

import dmitry.zhuchkov.component.mvp.MVPPresenter
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitry.zhuchkov.feature.rates.domain.usecase.CalculateRatesUseCase
import dmitry.zhuchkov.feature.rates.domain.usecase.GetBaseCurrencyUseCase
import dmitry.zhuchkov.feature.rates.domain.usecase.GetRatesUseCase
import dmitry.zhuchkov.feature.rates.domain.usecase.SetBaseCurrencyUseCase
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
class RatesPresenter @Inject constructor(
	private val getRatesUseCase: GetRatesUseCase,
	private val getBaseCurrencyUseCase: GetBaseCurrencyUseCase,
	private val setBaseCurrencyUseCase: SetBaseCurrencyUseCase,
	private val calculateRatesUseCase: CalculateRatesUseCase
) : MVPPresenter<RatesView>() {

	private companion object {
		const val BASE_ITEM_RATE = 1.0
	}

	private var ratesList: List<CurrencyRate> = emptyList()

	override fun onCreateView() {
		Flowable.interval(1, TimeUnit.SECONDS)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeBy(
				onNext = {
					viewState?.showProgress()
					loadRates()
				},
				onError = {
					handleError(it)
				}
			)
			.addTo(compositeDisposable)
	}

	private fun loadRates() {
		getRatesUseCase(getBaseCurrencyUseCase().code, getBaseCurrencyUseCase().value)
			.filter {
				it.firstOrNull()?.code == getBaseCurrencyUseCase().code
			}
			.map { calculateRatesUseCase(it, getBaseCurrencyUseCase(), getBaseCurrencyUseCase()) }
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeBy(
				onSuccess = { ratesList ->
					this.ratesList = ratesList

					viewState?.hideProgress()
					viewState?.showRates(ratesList)
				},
				onError = {
					handleError(it)
				}
			)
			.addTo(compositeDisposable)
	}

	private fun handleError(throwable: Throwable) {
		viewState?.hideProgress()
		viewState?.showError(throwable.localizedMessage)
	}

	fun onItemSelected(currencyRate: CurrencyRate) {
		setBaseCurrencyUseCase(currencyRate)

		viewState?.scrollToTop()
		viewState?.showProgress()
	}

	fun onCurrentRateChanged(value: String) {
		if (getBaseCurrencyUseCase().value == value.toDoubleOrNull()) {
			return
		}

		setBaseCurrencyUseCase(
			getBaseCurrencyUseCase().copy(
				value = value.toDoubleOrNull() ?: BASE_ITEM_RATE
			)
		)

		ratesList =
			calculateRatesUseCase(ratesList, getBaseCurrencyUseCase(), getBaseCurrencyUseCase())
		viewState?.showRates(ratesList)
	}
}