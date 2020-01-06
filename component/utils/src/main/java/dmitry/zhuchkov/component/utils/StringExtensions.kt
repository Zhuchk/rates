package dmitry.zhuchkov.component.utils

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
private const val DOT_DELIMETER = '.'
private const val COMMA_DELIMETER = ','

fun String.toDoubleOrDefault(default: Double? = null): Double? =
	this.replace(COMMA_DELIMETER, DOT_DELIMETER)
		.toDoubleOrNull() ?: default