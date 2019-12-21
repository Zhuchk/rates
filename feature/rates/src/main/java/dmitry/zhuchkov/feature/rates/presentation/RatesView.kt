package dmitry.zhuchkov.feature.rates.presentation

import dmitry.zhuchkov.component.mvp.MVPView
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
interface RatesView : MVPView {

	fun showRates(list: List<CurrencyRate>)

	fun scrollToTop()

	fun showError(message: String)

	fun showProgress()

	fun hideProgress()
}