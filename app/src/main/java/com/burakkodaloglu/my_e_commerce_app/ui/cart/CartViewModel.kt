package com.burakkodaloglu.my_e_commerce_app.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.ClearCart
import com.burakkodaloglu.my_e_commerce_app.data.model.DeleteCartRequest
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.data.model.ProductResponse
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.cart.CartParams
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.cart.ClearCartUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.cart.DeleteProductsCartUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.cart.GetCartProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val deleteCartProductsUseCase: DeleteProductsCartUseCase
) : ViewModel() {

    val getCartProductLiveData = MutableLiveData<AppResult<ProductResponse>>()
    val deleteProductCartLiveData = MutableLiveData<AppResult<CRUD>>()
    val clearCartLiveData = MutableLiveData<AppResult<CRUD>>()

    private val _totalAmount = MutableLiveData(0.0)
    val totalAmount: LiveData<Double> = _totalAmount
    fun getCartProducts(userId: String) {
        viewModelScope.launch {
            val result = getCartProductsUseCase(CartParams(userId))
            getCartProductLiveData.postValue(result)
        }
    }

    fun deleteCartProduct(itemId: Int, userId: String) {
        viewModelScope.launch {
            val result = deleteCartProductsUseCase(DeleteCartRequest(itemId, userId))
            getCartProducts(userId)
            deleteProductCartLiveData.postValue(result)
        }
    }

    fun clearCart(clearCart: ClearCart) {
        viewModelScope.launch {
            val result = clearCartUseCase(clearCart)
            clearCartLiveData.postValue(result)
        }
    }

    fun calcTotalAmount(cartList: List<Product>){
        var total = 0.0
        for (item in cartList) {
            total += item.salePrice
        }
        _totalAmount.value = total
    }

    fun increase(price: Double) {
        _totalAmount.value = _totalAmount.value?.plus(price)
    }

    fun decrease(price: Double) {
        _totalAmount.value = _totalAmount.value?.minus(price)
    }
}