package com.burakkodaloglu.my_e_commerce_app.ui.cart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.data.model.ClearCart
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentCartBinding
import com.burakkodaloglu.my_e_commerce_app.ui.cart.adapters.CartAdapter
import com.burakkodaloglu.my_e_commerce_app.util.common.ManagerDialog
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import com.burakkodaloglu.my_e_commerce_app.util.storage.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {
    private val managerAlertDialog by lazy { ManagerDialog(requireContext()) }
    private val binding by viewBinding(FragmentCartBinding::bind)
    private val cartAdapter by lazy { CartAdapter() }
    private val cartViewModel: CartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        val userId = SharedPrefManager.getInstance(requireContext()).data.userId
        cartViewModel.getCartProducts(userId)
        cartAdapter.onIncreaseClick = {
            cartViewModel.increase(it)
        }
        cartAdapter.onDecreaseClick = {
            cartViewModel.decrease(it)
        }
        cartAdapter.onDeleteClick = {
            cartViewModel.deleteCartProduct(it, userId)
        }
        binding.imgDelete.setOnClickListener {
            cartViewModel.clearCart(ClearCart(userId))
            binding.tvTotalAmount.setText("0.0$")
        }
    }

    private fun initObserver() {
        with(cartViewModel) {
            with(binding) {
                getCartProductLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        it.products?.let { it1 ->
                            cartAdapter.updateList(it1)
                            cartViewModel.calcTotalAmount(it1)
                            cartAdapter.apply {
                                rvCartProucts.setHasFixedSize(true)
                                rvCartProucts.adapter = cartAdapter
                            }
                            btnBuyNow.setOnClickListener {
                                findNavController().navigate(R.id.action_cartFragment_to_paymentFragment)
                            }
                        }
                    }.doOnFailure {
                        binding.tvTotalAmount.setText("0.0$")
                    }
                }
                deleteProductCartLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        cartAdapter.updateList(emptyList())
                    }.doOnFailure {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
                clearCartLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        cartAdapter.updateList(emptyList())
                        Toast.makeText(requireContext(), "Cart clear", Toast.LENGTH_LONG).show()
                    }.doOnFailure {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                    }
                }
                totalAmount.observe(viewLifecycleOwner) {
                    tvTotalAmount.text = String.format("%.3f$", it)
                }
            }
        }
    }
}