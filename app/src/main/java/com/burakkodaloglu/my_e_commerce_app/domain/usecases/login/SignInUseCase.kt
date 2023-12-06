package com.burakkodaloglu.my_e_commerce_app.domain.usecases.login

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.data.model.Login
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<Login, AppResult<LoginResponse>>() {
    override suspend fun execute(params: Login): AppResult<LoginResponse> =
        productRepository.login(params)
}