package com.burakkodaloglu.my_e_commerce_app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.model.CategoryBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.CategoryProductParams
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetCategoryListUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetCategoryProductUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetProductUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetSaleProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSaleProductUseCase: GetSaleProductUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getCategoryProductUseCase: GetCategoryProductUseCase,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {
    val productLiveData = MutableLiveData<AppResult<ProductBody>>()
    val getSaleProductLiveData = MutableLiveData<AppResult<ProductBody>>()
    val categoryListLiveData = MutableLiveData<AppResult<CategoryBody>>()
    val categoryProductLiveData = MutableLiveData<AppResult<ProductBody>>()

    init {
        viewModelScope.launch {
            getProduct()
            getCategoryList()
            getSaleProduct()
            getCategoryProduct(String())
        }
    }

    private fun getProduct() {
        viewModelScope.launch {
            val result = getProductUseCase(Unit)
            productLiveData.postValue(result)
        }
    }

    private fun getCategoryProduct(category: String) {
        if (category == "All") return getProduct()
        viewModelScope.launch {
            val result = getCategoryProductUseCase(CategoryProductParams(category))
            categoryProductLiveData.postValue(result)
        }
    }

    private fun getCategoryList() {
        val addCategory: MutableList<String> = mutableListOf()
        addCategory.add("All")
        viewModelScope.launch {
            val result = getCategoryListUseCase(Unit)
            categoryListLiveData.postValue(result)
        }
    }

    private fun getSaleProduct() {
        viewModelScope.launch {
            val result = getSaleProductUseCase(Unit)
            getSaleProductLiveData.postValue(result)
        }
    }
}