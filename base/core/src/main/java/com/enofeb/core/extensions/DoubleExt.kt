package com.enofeb.core.extensions

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffDecimal(): String {
    val df = DecimalFormat("#.#####")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(this).toString()
}