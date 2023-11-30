package com.burakkodaloglu.my_e_commerce_app.domain.usecases.home

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.data.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class GetCategoryProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<CategoryProductParams, AppResult<ProductBody>>() {
    override suspend fun execute(params: CategoryProductParams): AppResult<ProductBody> =
        productRepository.getCategory(params.category)
}
data class CategoryProductParams(val category: String)