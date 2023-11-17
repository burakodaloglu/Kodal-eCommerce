package com.burakkodaloglu.my_e_commerce_app.domain.model

data class LoginResponse(
    val message: String,
    val status: Int,
    val userId: String
)
