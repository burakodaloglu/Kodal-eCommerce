package com.burakkodaloglu.my_e_commerce_app.domain.usecases.cart

import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.DeleteCartRequest
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import javax.inject.Inject

class DeleteProductsCartUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<DeleteCartRequest, AppResult<CRUD>>() {
    override suspend fun execute(params: DeleteCartRequest): AppResult<CRUD> =
        productRepository.deleteProductCart(params)

}