package dmitry.zhuchkov.feature.rates.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dmitry.zhuchkov.component.ui.inflate
import dmitry.zhuchkov.component.utils.CurrencyFlagProvider
import dmitry.zhuchkov.feature.rates.R
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import kotlinx.android.synthetic.main.rate_list_item.view.*

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class RateItemViewHolder(
	private val parent: ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.rate_list_item)) {

	fun bind(
		currencyRate: CurrencyRate,
		onItemClicked: ((item: CurrencyRate, position: Int) -> Unit)?
	) {
		itemView.currencyCode.text = currencyRate.code
		itemView.fullName.text = currencyRate.fullName
		itemView.currencyValue.isFocusable = false
		itemView.currencyValue.setText(currencyRate.value.toString())

		Glide.with(parent.context)
			.load(CurrencyFlagProvider.getFlagByCode(currencyRate.code))
			.centerCrop()
			.into(itemView.currencyImage)

		itemView.setOnClickListener {
			onItemClicked?.invoke(currencyRate, adapterPosition)
		}
	}

	fun updateAmount(rate: Double) {
		itemView.currencyValue.setText(rate.toString())
	}
}