package dmitrii.zhuchkov.feature.rates.presentation

import dmitrii.zhuchkov.component.mvp.MVPView
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
interface RatesView : MVPView {

	fun showRates(list: List<CurrencyRate>, scrollToTop: Boolean = false)

	fun showError(message: String)

	fun showProgress()

	fun hideProgress()
}