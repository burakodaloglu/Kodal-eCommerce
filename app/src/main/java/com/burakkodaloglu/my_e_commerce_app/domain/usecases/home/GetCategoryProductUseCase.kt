package com.burakkodaloglu.my_e_commerce_app.domain.usecases.home

import com.burakkodaloglu.my_e_commerce_app.data.model.ProductResponse
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import javax.inject.Inject

class GetCategoryProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<CategoryProductParams, AppResult<ProductResponse>>() {
    override suspend fun execute(params: CategoryProductParams): AppResult<ProductResponse> =
        productRepository.getCategory(params.category)
}
data class CategoryProductParams(val category: String)