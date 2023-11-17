package com.burakkodaloglu.my_e_commerce_app.domain.model

data class UserResponse(
    val message: String,
    val status: Int,
    val user: User
)
