package com.burakkodaloglu.my_e_commerce_app.ui.paymentsuccess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentPaymentSuccessBinding
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentSuccessFragment : Fragment(R.layout.fragment_payment_success) {
    private val binding by viewBinding(FragmentPaymentSuccessBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContinueShopping.setOnClickListener { findNavController().navigate(R.id.action_paymentSuccessFragment_to_homeFragment) }
    }
}