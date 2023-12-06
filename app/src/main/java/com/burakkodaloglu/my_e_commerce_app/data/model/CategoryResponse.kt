package com.burakkodaloglu.my_e_commerce_app.data.model

data class CategoryResponse(
    val categories: List<String>,
    val message: String,
    val status: Int
)
