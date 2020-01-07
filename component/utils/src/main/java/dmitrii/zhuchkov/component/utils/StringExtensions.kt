package dmitrii.zhuchkov.component.utils

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
private const val DOT_DELIMITER = '.'
private const val COMMA_DiLIMITER = ','

fun String.toDoubleOrDefault(default: Double): Double =
	this.replace(COMMA_DiLIMITER, DOT_DELIMITER)
		.toDoubleOrNull() ?: default