package com.burakkodaloglu.my_e_commerce_app.domain.usecases.home

import com.burakkodaloglu.my_e_commerce_app.data.model.ProductResponse
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<SearchParams, AppResult<ProductResponse>>() {
    override suspend fun execute(params: SearchParams): AppResult<ProductResponse> =
        productRepository.searchProduct(params.query)

}

data class SearchParams(val query: String)