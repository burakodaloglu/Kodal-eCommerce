package com.burakkodaloglu.my_e_commerce_app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentHomeBinding
import com.burakkodaloglu.my_e_commerce_app.util.common.ManagerDialog
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val managerAlertDialog by lazy { ManagerDialog(requireContext()) }
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val saleProductsAdapter by lazy { SaleProductsAdapter() }
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        saleProductsAdapter.onFavoriteClick = {
        }

        saleProductsAdapter.onProductClick = {
        }
        homeViewModel.getSaleProduct()
    }

    private fun initObservers() {
        with(binding) {
            with(homeViewModel) {
                getSaleProductLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        it.products?.let {
                            saleProductsAdapter.updateList(it)
                            val compositePageTransformer = CompositePageTransformer()
                            compositePageTransformer.addTransformer { page, position ->
                                val r = 1 - abs(position)
                                page.scaleY = (0.85f + r * 0.15f)
                            }

                            vpSaleProduct.apply {
                                adapter = saleProductsAdapter
                                clipToPadding = false
                                clipChildren = false
                                offscreenPageLimit = 3
                                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                                setPageTransformer(compositePageTransformer)
                                currentItem = 2
                            }
                        }
                    }.doOnFailure {
                        managerAlertDialog.showAlertDialog(
                            "",
                            (it.toString()),
                            "Close", {}, "", {}, customView = null, false
                        )
                    }
                }
            }
        }
    }
}