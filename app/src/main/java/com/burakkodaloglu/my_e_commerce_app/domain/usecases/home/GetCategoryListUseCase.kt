package com.burakkodaloglu.my_e_commerce_app.domain.usecases.home

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.data.model.CategoryResponse
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<Unit, AppResult<CategoryResponse>>() {
    override suspend fun execute(params: Unit): AppResult<CategoryResponse> =
        productRepository.getCategoryList()
}