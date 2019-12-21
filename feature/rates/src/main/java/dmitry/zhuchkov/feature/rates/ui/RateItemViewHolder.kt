package dmitry.zhuchkov.feature.rates.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dmitry.zhuchkov.component.ui.inflate
import dmitry.zhuchkov.feature.rates.R
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import kotlinx.android.synthetic.main.rate_list_item.view.*

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class RateItemViewHolder(
	parent: ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.rate_list_item)) {

	fun bind(
		currencyRate: CurrencyRate,
		onItemClicked: ((item: CurrencyRate, position: Int) -> Unit)?
	) {
		itemView.currencyCode.text = currencyRate.name
		itemView.currencyValue.setText(currencyRate.value.toString())

		itemView.setOnClickListener {
			onItemClicked?.invoke(currencyRate, adapterPosition)
		}
	}

	fun updateAmount(rate: Double) {
		itemView.currencyValue.setText(rate.toString())
	}
}