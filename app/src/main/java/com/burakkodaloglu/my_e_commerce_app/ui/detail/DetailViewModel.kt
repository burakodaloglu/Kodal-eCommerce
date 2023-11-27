package com.burakkodaloglu.my_e_commerce_app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.burakkodaloglu.my_e_commerce_app.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val productModel = savedStateHandle.get<Product>("product")

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        getProduct()
    }

    private fun getProduct() {
        productModel?.let {
            _product.value = it
        }
    }
    fun setFavoriteState() {
        productModel?.let {
            if (_isFavorite.value == true) {
                _isFavorite.value = false
            } else {
                _isFavorite.value = true
            }
        }
    }
}