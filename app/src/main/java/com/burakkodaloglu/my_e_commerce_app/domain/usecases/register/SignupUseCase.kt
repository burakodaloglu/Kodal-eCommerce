package com.burakkodaloglu.my_e_commerce_app.domain.usecases.register

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.Signup
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : SuspendUseCase<Signup, AppResult<CRUD>>() {
    override suspend fun execute(params: Signup): AppResult<CRUD> =
        productRepository.signup(params)

}