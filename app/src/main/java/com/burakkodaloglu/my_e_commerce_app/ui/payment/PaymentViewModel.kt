package com.burakkodaloglu.my_e_commerce_app.ui.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.ClearCart
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.cart.ClearCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val clearCartUseCase: ClearCartUseCase) :
    ViewModel() {

    var clearCartLiveData = MutableLiveData<AppResult<CRUD>>()


    fun clearCart(clearCart: ClearCart) {
        viewModelScope.launch {
            val result = clearCartUseCase(clearCart)
            clearCartLiveData.postValue(result)
        }
    }
}
