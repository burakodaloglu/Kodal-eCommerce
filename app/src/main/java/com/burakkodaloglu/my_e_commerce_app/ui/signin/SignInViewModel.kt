package com.burakkodaloglu.my_e_commerce_app.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.model.LoginBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.login.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {

    var signinLiveData = MutableLiveData<AppResult<LoginResponse>>()

    fun signin(loginBody: LoginBody) {
        viewModelScope.launch {
            val result = signInUseCase(loginBody)
            signinLiveData.postValue(result)
        }
    }

}