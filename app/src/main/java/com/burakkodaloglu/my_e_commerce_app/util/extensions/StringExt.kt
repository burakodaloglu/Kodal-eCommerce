package com.burakkodaloglu.my_e_commerce_app.util.extensions

fun String?.orDefault(default: String): String {
    return this ?: default
}