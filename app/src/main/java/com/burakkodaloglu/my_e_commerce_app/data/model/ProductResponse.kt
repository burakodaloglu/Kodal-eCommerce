package com.burakkodaloglu.my_e_commerce_app.data.model

data class ProductResponse(
    val message: String,
    val products: List<Product>?,
    val status: Int
)
