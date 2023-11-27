package com.burakkodaloglu.my_e_commerce_app.domain.usecases.home

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.SuspendUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.model.CategoryBody
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(private val productRepository: ProductRepository) :
    SuspendUseCase<Unit, AppResult<CategoryBody>>() {
    override suspend fun execute(params: Unit): AppResult<CategoryBody> =
        productRepository.getCategoryList()
}