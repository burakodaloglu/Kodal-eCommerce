package com.burakkodaloglu.my_e_commerce_app.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentHomeBinding
import com.burakkodaloglu.my_e_commerce_app.ui.home.adapters.CategoryListAdapter
import com.burakkodaloglu.my_e_commerce_app.ui.home.adapters.CategoryProductsAdapter
import com.burakkodaloglu.my_e_commerce_app.ui.home.adapters.SaleProductsAdapter
import com.burakkodaloglu.my_e_commerce_app.util.common.ManagerDialog
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val managerAlertDialog by lazy { ManagerDialog(requireContext()) }
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val saleProductsAdapter by lazy { SaleProductsAdapter() }
    private val categoryListAdapter by lazy { CategoryListAdapter() }
    private val categoryProductAdapter by lazy { CategoryProductsAdapter() }
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        saleProductsAdapter.onProductClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        categoryProductAdapter.onProductClick={
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun initObservers() {
        with(binding) {
            with(homeViewModel) {
                /*
                productLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        it.products?.let { it1 -> categoryProductAdapter.updateList(it1) }
                    }.doOnFailure {
                        managerAlertDialog.showAlertDialog(
                            "",
                            (it.toString()),
                            "Close", {}, "", {}, customView = null, false
                        )
                    }
                }
                 */
                getSaleProductLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        it.products?.let {
                            val compositePageTransformer = CompositePageTransformer()
                            compositePageTransformer.addTransformer { page, position ->
                                val r = 1 - abs(position)
                                page.scaleY = (0.85f + r * 0.15f)
                            }
                            vpSaleProduct.apply {
                                adapter = saleProductsAdapter
                                saleProductsAdapter.updateList(it)
                                clipToPadding = false
                                clipChildren = false
                                offscreenPageLimit = 3
                                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                                setPageTransformer(compositePageTransformer)
                                currentItem = 4
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
                categoryListLiveData.observe(viewLifecycleOwner) {
                    val addCategory: MutableList<String> = mutableListOf()
                    addCategory.add("All")
                    it.doOnSuccess {
                        addCategory.addAll(it.categories)
                        categoryListAdapter.updateCategoryName(addCategory)
                        rvCategory.apply {
                            setHasFixedSize(true)
                            layoutManager =
                                LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                            adapter = categoryListAdapter
                        }
                    }.doOnFailure {
                        managerAlertDialog.showAlertDialog(
                            "",
                            (it.toString()),
                            "Close", {}, "", {}, customView = null, false
                        )
                    }
                }
                productLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        it.products?.let { it1 ->
                            categoryProductAdapter.updateList(it1)
                            rvCategoryProduct.apply {
                                setHasFixedSize(true)
                                adapter = categoryProductAdapter
                            }
                        }
                    }.doOnFailure {
                        managerAlertDialog.showAlertDialog(
                            "",
                            (it.toString()),
                            "Close", {}, "", {}, customView = null, false)
                    }
                }
            }
        }
    }
}