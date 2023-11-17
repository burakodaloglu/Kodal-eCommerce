package com.burakkodaloglu.my_e_commerce_app.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.domain.model.SignupBody
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.register.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignupUseCase
) : ViewModel() {

    var signUpLiveData = MutableLiveData<AppResult<CRUD>>()

    fun signUp(signUpBody: SignupBody) {
        viewModelScope.launch {
            val result = signUpUseCase(signUpBody)
            signUpLiveData.postValue(result)
        }
    }
}