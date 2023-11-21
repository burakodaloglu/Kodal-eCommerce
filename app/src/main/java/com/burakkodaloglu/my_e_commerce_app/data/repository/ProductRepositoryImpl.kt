package com.burakkodaloglu.my_e_commerce_app.data.repository

import com.burakkodaloglu.my_e_commerce_app.data.source.remote.ProductService
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.domain.model.LoginBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.domain.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.SignupBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.UserResponse
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productService: ProductService) :
    ProductRepository {

    override suspend fun getSaleProduct(): AppResult<ProductBody> = productService.getSaleProduct()

    override suspend fun login(loginBody: LoginBody): AppResult<LoginResponse> =
        productService.login(loginBody)

    override suspend fun signup(signUpBody: SignupBody): AppResult<CRUD> =
        productService.register(signUpBody)

    override suspend fun getUser(userId: String): AppResult<UserResponse> =
        productService.getUser(userId)
}