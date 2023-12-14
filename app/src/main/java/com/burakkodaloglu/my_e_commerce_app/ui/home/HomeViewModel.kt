package com.burakkodaloglu.my_e_commerce_app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.data.model.CategoryResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.data.model.ProductResponse
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.favorite.AddFavoriteUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.favorite.DeleteProductFavoriteUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.CategoryProductParams
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetCategoryListUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetCategoryProductUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetProductUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetSaleProductUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.SearchParams
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSaleProductUseCase: GetSaleProductUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getCategoryProductUseCase: GetCategoryProductUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteProductFavoriteUseCase: DeleteProductFavoriteUseCase,
    private val searchProductUseCase: SearchProductUseCase
) : ViewModel() {
    val productLiveData = MutableLiveData<AppResult<ProductResponse>>()
    val getSaleProductLiveData = MutableLiveData<AppResult<ProductResponse>>()
    val categoryListLiveData = MutableLiveData<AppResult<CategoryResponse>>()
    val categoryProductLiveData = MutableLiveData<AppResult<ProductResponse>>()
    val searchProductLiveData = MutableLiveData<AppResult<ProductResponse>>()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            getProduct()
            getCategoryList()
            getSaleProduct()
            getCategoryProduct(String())
        }
    }

    fun deleteFavoriteProduct(product: Product) {
        viewModelScope.launch { deleteProductFavoriteUseCase(product) }
    }

    fun addFavoriteProduct(product: Product) {
        viewModelScope.launch {
            addFavoriteUseCase(product)
        }
    }

    fun getProduct() {
        viewModelScope.launch {
            val result = getProductUseCase(Unit)
            productLiveData.postValue(result)
        }
    }

    fun searchProduct(query: String) {
        viewModelScope.launch {
            val result = searchProductUseCase(SearchParams(query))
            searchProductLiveData.postValue(result)
        }
    }

    fun getCategoryProduct(category: String) {
        if (category == "All"|| category=="") return getProduct()
        viewModelScope.launch {
                val result = getCategoryProductUseCase(CategoryProductParams(category))
                categoryProductLiveData.postValue(result)
            }
    }

    fun getCategoryList() {
        val addCategory: MutableList<String> = mutableListOf()
        addCategory.add("All")
        viewModelScope.launch {
            val result = getCategoryListUseCase(Unit)
            categoryListLiveData.postValue(result)
        }
    }

    fun getSaleProduct() {
        viewModelScope.launch {
            val result = getSaleProductUseCase(Unit)
            getSaleProductLiveData.postValue(result)
        }
    }
}