package com.burakkodaloglu.my_e_commerce_app.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.model.UserResponse
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.profile.ProfileUseCase
import com.burakkodaloglu.my_e_commerce_app.domain.usecases.profile.UseParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) :
    ViewModel() {

    var userLiveData = MutableLiveData<AppResult<UserResponse>>()

    fun getUser(userId: String) {
        viewModelScope.launch {
            val result = profileUseCase(UseParams(userId))
            userLiveData.postValue(result)
        }
    }
}