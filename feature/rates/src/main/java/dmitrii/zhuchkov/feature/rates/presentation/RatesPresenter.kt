package dmitrii.zhuchkov.feature.rates.presentation

import dmitrii.zhuchkov.component.mvp.MVPPresenter
import dmitrii.zhuchkov.component.utils.toDoubleOrDefault
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.domain.usecase.CalculateRatesUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.GetBaseCurrencyUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.GetRatesUseCase
import dmitrii.zhuchkov.feature.rates.domain.usecase.SetBaseCurrencyUseCase
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.math.BigDecimal
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
		subscribeOnRates()
	}

	private fun subscribeOnRates() {
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
		unsubscribeFromRates()
		ratesList = ratesList.moveItemToTop(currencyRate)

		setBaseCurrencyUseCase(currencyRate)

		viewState?.showRates(ratesList, true)

		subscribeOnRates()
	}

	private fun List<CurrencyRate>.moveItemToTop(currencyRate: CurrencyRate): List<CurrencyRate> {
		val newList = this
			.filter { it.code != currencyRate.code }
			.toMutableList()

		newList.add(0, currencyRate)

		return newList
	}

	private fun unsubscribeFromRates() {
		compositeDisposable.clear()
		viewState?.hideProgress()
	}

	fun onCurrentRateChanged(value: String) {
		val newValue = BigDecimal.valueOf(value.toDoubleOrDefault(BASE_ITEM_RATE))
		if (getBaseCurrencyUseCase().value == newValue) {
			return
		}

		setBaseCurrencyUseCase(
			getBaseCurrencyUseCase().copy(
				value = newValue
			)
		)

		ratesList =
			calculateRatesUseCase(ratesList, getBaseCurrencyUseCase(), getBaseCurrencyUseCase())
		viewState?.showRates(ratesList)
	}
}