package com.burakkodaloglu.my_e_commerce_app.domain.usecases.favorite

import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class GetFavoriteProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<Unit, List<Product>>() {
    override suspend fun execute(params: Unit): List<Product> =
        productRepository.getFavoriteProduct()
}