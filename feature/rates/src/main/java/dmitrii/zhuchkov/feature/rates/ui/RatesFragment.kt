package dmitrii.zhuchkov.feature.rates.ui

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dmitrii.zhuchkov.component.mvp.MVPFragment
import dmitrii.zhuchkov.component.ui.hideSoftKeyboard
import dmitrii.zhuchkov.feature.rates.R
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import dmitrii.zhuchkov.feature.rates.presentation.RatesPresenter
import dmitrii.zhuchkov.feature.rates.presentation.RatesView
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
		rateRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
				if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
					requireActivity().hideSoftKeyboard()
				}
			}
		})

		rateRecycler.itemAnimator = null
	}

	override fun showRates(list: List<CurrencyRate>, scrollToTop: Boolean) {
		dispatchDiff(adapter.items, list, scrollToTop)
	}

	override fun showError(message: String) {
		Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
	}

	override fun showProgress() {
		progressBar.isVisible = true
		rateRecycler.isClickable = false
	}

	override fun hideProgress() {
		progressBar.isVisible = false
		rateRecycler.isClickable = true
	}

	private fun dispatchDiff(oldItems: List<CurrencyRate>, newItems: List<CurrencyRate>, scrollToTop: Boolean) {
		Single.fromCallable {
			val diffCallback = RatesDiffCallback(oldItems, newItems)
			DiffUtil.calculateDiff(diffCallback)
		}
			.subscribeOn(Schedulers.computation())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribeBy {
				adapter.items = newItems
				it.dispatchUpdatesTo(adapter)

				if (scrollToTop) {
					rateRecycler.smoothScrollToPosition(0)
				}
			}
	}
}