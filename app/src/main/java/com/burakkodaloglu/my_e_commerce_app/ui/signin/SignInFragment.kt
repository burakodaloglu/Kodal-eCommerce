package com.burakkodaloglu.my_e_commerce_app.ui.signin

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentSignInBinding
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginBody
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.util.common.ManagerDialog
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import com.burakkodaloglu.my_e_commerce_app.util.storage.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val managerAlertDialog by lazy { ManagerDialog(requireContext()) }
    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val signInViewModel: SignInViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        binding.tvDonthaveanaccount.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        with(binding) {
            loginBtn.setOnClickListener {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                if (Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches() && isEligibleToLogin(password)
                ) {
                    signInViewModel.signin(LoginBody(email, password))
                }
            }
        }
    }

    private fun initObserver() {
        signInViewModel.signinLiveData.observe(viewLifecycleOwner) {
            it.doOnSuccess {
                val userId = it.userId
                val message = it.message
                val status = it.status
                SharedPrefManager.getInstance(requireActivity())
                    .saveUser(LoginResponse(message, status, userId))
                findNavController().navigate(R.id.signInToMainGraph)
            }.doOnFailure {
                managerAlertDialog.showAlertDialog(
                    "",
                    (it.toString()),
                    "Tamam", {}, "", {}, customView = null, false
                )
            }
        }
    }

    private fun isEligibleToLogin(
        password: String
    ): Boolean {
        return when {
            password.isEmpty() -> {
                binding.etPassword.error = "Lütfen şifrenizi giriniz."
                binding.etPassword.requestFocus()
                false
            }

            password.length < 6 -> {
                binding.etPassword.error = "Şifreniz 6 karakterden az olamaz!"
                binding.etPassword.requestFocus()
                false
            }

            else -> true
        }
    }
}