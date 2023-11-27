package com.burakkodaloglu.my_e_commerce_app.domain.usecases.home

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<Unit, AppResult<ProductBody>>() {
    override suspend fun execute(params: Unit): AppResult<ProductBody> =
        productRepository.getProduct()
}