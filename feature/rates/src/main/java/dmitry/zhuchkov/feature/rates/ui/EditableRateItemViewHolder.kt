package dmitry.zhuchkov.feature.rates.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dmitry.zhuchkov.component.ui.inflate
import dmitry.zhuchkov.component.ui.textChangeListener
import dmitry.zhuchkov.component.utils.CurrencyFlagProvider
import dmitry.zhuchkov.feature.rates.R
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
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
		itemView.currencyValue.setText(currencyRate.value.toString())

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