package dmitry.zhuchkov.component.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes

/**
 * Created by Dmitrii on 15.12.2019
 *
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
	LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun EditText.textChangeListener(
	onTextChanged: ((text: String) -> Unit)? = null,
	afterTextChanged: ((text: String) -> Unit)? = null,
	beforeTextChanged: ((text: String) -> Unit)? = null
) {
	addTextChangedListener(object : TextWatcher {

		override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
			onTextChanged?.invoke(charSequence.toString())
		}

		override fun afterTextChanged(editable: Editable?) {
			afterTextChanged?.invoke(editable.toString())
		}

		override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
			beforeTextChanged?.invoke(charSequence.toString())
		}
	})
}