package dmitry.zhuchkov.feature.rates.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dmitry.zhuchkov.component.ui.inflate
import dmitry.zhuchkov.component.ui.textChangeListener
import dmitry.zhuchkov.feature.rates.R
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate
import kotlinx.android.synthetic.main.rate_list_item.view.*

/**
 * Created by Dmitrii on 19.12.2019
 *
 */
class EditableRateItemViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.rate_list_item)) {

    fun bind(
        currencyRate: CurrencyRate,
        onRateChanged: ((rate: String) -> Unit)?
    ) {
        itemView.currencyCode.text = currencyRate.name
        itemView.currencyValue.setText(currencyRate.value.toString())

        itemView.currencyValue.textChangeListener(
            afterTextChanged = {
                onRateChanged?.invoke(it)
            }
        )
    }

    fun updateAmount(amount: Double) {
        itemView.currencyValue.setText(amount.toString())
    }
}