package com.burakkodaloglu.my_e_commerce_app.domain.model

data class ProductBody(
    val message: String,
    val products: List<Product>?,
    val status: Int
)
