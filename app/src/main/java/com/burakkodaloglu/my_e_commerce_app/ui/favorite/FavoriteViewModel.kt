package com.burakkodaloglu.my_e_commerce_app.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.favorite.AddFavoriteUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.favorite.DeleteProductFavoriteUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.favorite.GetFavoriteProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val deleteProductFavoriteUseCase: DeleteProductFavoriteUseCase,
    private val getFavoriteProductUseCase: GetFavoriteProductUseCase
) : ViewModel() {
    var getFavoriteLiveData = MutableLiveData<List<Product>>()
    var deleteProductFavorites = MutableLiveData<Unit>()

    init {
        getFavorite()
    }

    fun getFavorite() {
        viewModelScope.launch {
            val result = getFavoriteProductUseCase(Unit)
            getFavoriteLiveData.postValue(result)
        }
    }

    fun deleteProductFavorite(productEntity: Product) {
        viewModelScope.launch {
            val result = deleteProductFavoriteUseCase(productEntity)
            deleteProductFavorites.postValue(result)
            getFavorite()
        }
    }
}