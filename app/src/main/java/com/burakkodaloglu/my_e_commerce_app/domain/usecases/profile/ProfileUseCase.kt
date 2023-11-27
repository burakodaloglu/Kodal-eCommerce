package com.burakkodaloglu.my_e_commerce_app.domain.usecases.profile

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.model.UserResponse
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<UseParams, AppResult<UserResponse>>() {
    override suspend fun execute(params: UseParams): AppResult<UserResponse> =
        productRepository.getUser(params.userId)
}

data class UseParams(val userId: String)