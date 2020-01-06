package dmitrii.zhuchkov.feature.rates.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dmitrii.zhuchkov.component.ui.inflate
import dmitrii.zhuchkov.component.ui.textChangeListener
import dmitrii.zhuchkov.component.utils.CurrencyFlagProvider
import dmitrii.zhuchkov.feature.rates.R
import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import kotlinx.android.synthetic.main.rate_list_item.view.*

/**
 * Created by Dmitrii on 19.12.2019
 *
 */
class EditableRateItemViewHolder(
	private val parent: ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.rate_list_item)) {

	fun bind(
		currencyRate: CurrencyRate,
		onRateChanged: ((rate: String) -> Unit)?
	) {
		itemView.currencyCode.text = currencyRate.code
		itemView.fullName.text = currencyRate.fullName
		val amount = parent.context.getString(R.string.amountFormat).format(currencyRate.value)
		itemView.currencyValue.setText(amount)

		Glide.with(parent.context)
			.load(CurrencyFlagProvider.getFlagByCode(currencyRate.code))
			.centerCrop()
			.into(itemView.currencyImage)

		itemView.currencyValue.textChangeListener(
			afterTextChanged = {
				onRateChanged?.invoke(it)
			}
		)
	}
}