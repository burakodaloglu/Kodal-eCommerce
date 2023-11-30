package com.burakkodaloglu.my_e_commerce_app.data.model.ui

import com.burakkodaloglu.my_e_commerce_app.data.model.Product

data class UiProduct(
    val product: Product,
    val isFavorite: Boolean = false
)
