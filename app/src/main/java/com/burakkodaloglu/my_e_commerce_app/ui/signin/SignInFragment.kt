package com.burakkodaloglu.my_e_commerce_app.ui.signin

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentSignInBinding
import com.burakkodaloglu.my_e_commerce_app.data.model.Login
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
                if (checkRegex()) {
                    signInViewModel.signin(Login(email, password))
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

    fun checkRegex(): Boolean {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        var check = true
        val mailRegex = "(\\W|^)[\\w.+\\-]*@gmail\\.com(\\W|\$)".toRegex()


        if (email.isEmpty()) {
            binding.tilEmail.helperText = "Your email field is empty."
            check = false
        }
        if (!mailRegex.containsMatchIn(email)) {
            binding.tilEmail.helperText = "Enter you gmail. (ex: @gmail.com)"
            check = false
        }
        if (password.isEmpty()) {
            binding.tilPassword.helperText = "Your password field is empty."
            check = false
        }
        if (password.length < 6) {
            binding.tilPassword.helperText = "Your password can consist of at least 6 characters."
            check = false
        }
        return check
    }
}