package dmitrii.zhuchkov.feature.rates.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dmitrii.zhuchkov.component.ui.inflate
import dmitrii.zhuchkov.component.utils.CurrencyFlagProvider
import dmitrii.zhuchkov.feature.rates.R
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
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
		val amount = parent.context.getString(R.string.amountFormat).format(currencyRate.value)
		itemView.currencyValue.setText(amount)

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