package com.burakkodaloglu.my_e_commerce_app.ui.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentSignUpBinding
import com.burakkodaloglu.my_e_commerce_app.data.model.Signup
import com.burakkodaloglu.my_e_commerce_app.util.common.ManagerDialog
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val managerAlertDialog by lazy { ManagerDialog(requireContext()) }
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val signupViewModel: SignUpViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        with(binding) {
            tvDoHaveAnAccount.setOnClickListener {
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }
            btnRegister.setOnClickListener {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                if (checkRegex()) {
                    signupViewModel.signUp(Signup(email, password))
                }
            }
        }
    }

    private fun initObserver() {
        signupViewModel.signUpLiveData.observe(viewLifecycleOwner) {
            it.doOnSuccess {
                Toast.makeText(requireContext(), "Your account has been created.", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }.doOnFailure {
                managerAlertDialog.showAlertDialog(
                    "",
                    (it.message),
                    "Tamam", {}, "", {}, customView = null, false
                )
            }
        }
    }

    fun checkRegex(): Boolean {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        var check = true
        val capitalRegex = "^(?=.*[A-Z]{1})".toRegex()
        val numberRegex = "^(?=.*[1234567890]{1})".toRegex()
        val hyphensRegex = "^(?=.*[-]{1})".toRegex()
        val mailRegex = "(\\W|^)[\\w.+\\-]*@gmail\\.com(\\W|\$)".toRegex()


        if (email.isEmpty()) {
            binding.tilEmail.helperText = "Your email field is empty."
            check=false
        }
        if (password.isEmpty()) {
            binding.tilPassword.helperText = "Your password field is empty."
            check=false
        }
        if (password.length < 6) {
            binding.tilPassword.helperText = "Your password can consist of at least 6 characters."
            check = false
        }
        if (!mailRegex.containsMatchIn(email)) {
            binding.tilEmail.helperText = "Enter you gmail. (ex: @gmail.com)"
            check = false
        }

        if (!capitalRegex.containsMatchIn(password)) {
            binding.tilPassword.helperText = "Minimum 1 capital letters required in password field."
            check = false
        }

        if (!numberRegex.containsMatchIn(password)) {
            binding.tilPassword.helperText = "Minimum 1 number required in password field."
            check = false
        }

        if (!hyphensRegex.containsMatchIn(password)) {
            binding.tilPassword.helperText = "Minimum 1 hyphens required in password field."
            check = false
        }
        return check
    }
}