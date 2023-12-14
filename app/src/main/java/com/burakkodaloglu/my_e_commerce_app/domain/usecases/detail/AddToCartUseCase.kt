package com.burakkodaloglu.my_e_commerce_app.domain.usecases.detail

import com.burakkodaloglu.my_e_commerce_app.data.model.AddToCart
import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<AddToCart, AppResult<CRUD>>() {
    override suspend fun execute(params: AddToCart): AppResult<CRUD> =
        productRepository.addToCart(params)
}