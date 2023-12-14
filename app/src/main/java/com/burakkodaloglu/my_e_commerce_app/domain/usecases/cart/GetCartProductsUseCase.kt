package com.burakkodaloglu.my_e_commerce_app.domain.usecases.cart

import com.burakkodaloglu.my_e_commerce_app.data.model.ProductResponse
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import javax.inject.Inject

class GetCartProductsUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<CartParams, AppResult<ProductResponse>>() {
    override suspend fun execute(params: CartParams): AppResult<ProductResponse> =
        productRepository.getCartProducts(params.userId)
}

data class CartParams(val userId: String)