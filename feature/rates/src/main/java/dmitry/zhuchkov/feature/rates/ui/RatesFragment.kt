package dmitry.zhuchkov.feature.rates.ui

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import dmitry.zhuchkov.component.mvp.MVPFragment
import dmitry.zhuchkov.feature.rates.R
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitry.zhuchkov.feature.rates.presentation.RatesPresenter
import dmitry.zhuchkov.feature.rates.presentation.RatesView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.rates_fragment.*
import javax.inject.Inject

/**
 * Created by Dmitrii on 12.12.2019
 *
 */
class RatesFragment : MVPFragment(), RatesView {

	override val layoutRes: Int = R.layout.rates_fragment

	private val adapter = RatesAdapter()

	@Inject
	lateinit var presenter: RatesPresenter

	override fun setupView() {
		adapter.onItemClicked = presenter::onItemSelected
		adapter.onRateChanged = presenter::onCurrentRateChanged
		rateRecycler.adapter = adapter
	}

	override fun showRates(list: List<CurrencyRate>) {
		dispatchDiff(adapter.items, list)
	}

	override fun scrollToTop() {
		rateRecycler.smoothScrollToPosition(0)
	}

	override fun showError(message: String) {
		Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
	}

	override fun showProgress() {
		progressBar.isVisible = true
		rateRecycler.isVisible = false
	}

	override fun hideProgress() {
		progressBar.isVisible = false
		rateRecycler.isVisible = true
	}

	private fun dispatchDiff(oldItems: List<CurrencyRate>, newItems: List<CurrencyRate>) {
		Single.fromCallable {
			val diffCallback = RatesDiffCallback(oldItems, newItems)
			DiffUtil.calculateDiff(diffCallback)
		}.subscribeOn(Schedulers.computation())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeBy {
				adapter.items = newItems
				it.dispatchUpdatesTo(adapter)
			}
	}
}