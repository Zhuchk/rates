package dmitry.zhuchkov.feature.rates.ui

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import dmitry.zhuchkov.feature.rates.domain.entity.CurrencyRate

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
class RatesDiffCallback(
	private val oldList: List<CurrencyRate>,
	private val newList: List<CurrencyRate>
) : DiffUtil.Callback() {

	override fun getOldListSize(): Int = oldList.size

	override fun getNewListSize(): Int = newList.size

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
		oldList[oldItemPosition].code == newList[newItemPosition].code

	override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean =
		oldList[oldPosition] == newList[newPosition]

	@Nullable
	override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
		RatesChangePayLoad(newList[newItemPosition].value)
}

data class RatesChangePayLoad(val amount: Double)