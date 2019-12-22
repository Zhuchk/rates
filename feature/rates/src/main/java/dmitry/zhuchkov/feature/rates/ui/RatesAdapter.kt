package dmitry.zhuchkov.feature.rates.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class RatesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private companion object {
		const val EDITABLE_VIEW_TYPE = 0
		const val RATE_VIEW_TYPE = 1
	}

	var onItemClicked: ((item: CurrencyRate) -> Unit)? = null
	var onRateChanged: ((rate: String) -> Unit)? = null

	var items: List<CurrencyRate> = emptyList()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
		when (viewType) {
			RATE_VIEW_TYPE     -> RateItemViewHolder(parent)
			EDITABLE_VIEW_TYPE -> EditableRateItemViewHolder(parent)

			else               -> RateItemViewHolder(parent)
		}

	override fun getItemCount(): Int =
		items.size

	override fun getItemViewType(position: Int): Int =
		if (position == 0) {
			EDITABLE_VIEW_TYPE
		} else {
			RATE_VIEW_TYPE
		}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (holder) {
			is RateItemViewHolder         -> holder.bind(items[position], ::onItemClicked)
			is EditableRateItemViewHolder -> holder.bind(items[position], onRateChanged)
		}
	}

	override fun onBindViewHolder(
		holder: RecyclerView.ViewHolder,
		position: Int,
		payloads: MutableList<Any>
	) {
		(payloads
			.firstOrNull() as? RatesChangePayLoad)?.let { payLoad ->
			when (holder) {
				is RateItemViewHolder         -> holder.updateAmount(payLoad.amount)
			}
		} ?: super.onBindViewHolder(holder, position, payloads)
	}

	private fun onItemClicked(item: CurrencyRate, position: Int) {
		onItemClicked?.invoke(item)
		notifyItemMoved(position, 0)
	}
}