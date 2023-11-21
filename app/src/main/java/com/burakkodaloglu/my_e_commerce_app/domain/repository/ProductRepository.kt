package com.burakkodaloglu.my_e_commerce_app.domain.repository

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.domain.model.LoginBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.domain.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.SignupBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.UserResponse

interface ProductRepository {
    suspend fun getSaleProduct(): AppResult<ProductBody>
    suspend fun login(loginBody: LoginBody): AppResult<LoginResponse>
    suspend fun signup(signUpBody: SignupBody): AppResult<CRUD>
    suspend fun getUser(userId: String): AppResult<UserResponse>

}