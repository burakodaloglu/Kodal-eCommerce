package com.burakkodaloglu.my_e_commerce_app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.home.GetSaleProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getSaleProductUseCase: GetSaleProductUseCase) :
    ViewModel() {
    val getSaleProductLiveData = MutableLiveData<AppResult<ProductBody>>()

    fun getSaleProduct() {
        viewModelScope.launch {
            val result = getSaleProductUseCase(Unit)
            getSaleProductLiveData.postValue(result)
        }
    }
}