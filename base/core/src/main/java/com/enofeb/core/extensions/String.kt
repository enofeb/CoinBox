package com.enofeb.core.extensions

fun String.addVolPrefix(): String {
    return "Vol $this"
}

fun String.addPercentage(): String {
    return "$this %"
}