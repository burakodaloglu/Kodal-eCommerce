package com.burakkodaloglu.my_e_commerce_app.domain.usecases.register

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.domain.model.SignupBody
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : SuspendUseCase<SignupBody, AppResult<CRUD>>() {
    override suspend fun execute(params: SignupBody): AppResult<CRUD> =
        productRepository.signup(params)

}