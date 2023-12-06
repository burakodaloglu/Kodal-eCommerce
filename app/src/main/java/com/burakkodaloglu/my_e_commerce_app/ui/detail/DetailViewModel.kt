package com.burakkodaloglu.my_e_commerce_app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.data.model.AddToCart
import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.detail.AddToCartUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.favorite.AddFavoriteUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.favorite.DeleteProductFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteProductFavoriteUseCase: DeleteProductFavoriteUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val productModel = savedStateHandle.get<Product>("product")

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    var addCartLiveData = MutableLiveData<AppResult<CRUD>>()

    init {
        getProduct()
        _isFavorite.value = productModel?.isFavorite
    }

    fun addToCart(addToCart: AddToCart) {
        viewModelScope.launch {
            val result: AppResult<CRUD> = addToCartUseCase(addToCart)
            addCartLiveData.postValue(result)
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

    private fun getProduct() {
        productModel?.let {
            _product.value = it
        }
    }

    fun setFavoriteState() {
        productModel?.let {
            if (_isFavorite.value == true) {
                deleteFavoriteProduct(it)
                _isFavorite.value = false
            } else {
                addFavoriteProduct(it)
                _isFavorite.value = true
            }
        }
    }
}