package com.burakkodaloglu.my_e_commerce_app.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentHomeBinding
import com.burakkodaloglu.my_e_commerce_app.ui.home.adapters.CategoryListAdapter
import com.burakkodaloglu.my_e_commerce_app.ui.home.adapters.CategoryProductsAdapter
import com.burakkodaloglu.my_e_commerce_app.ui.home.adapters.SaleProductsAdapter
import com.burakkodaloglu.my_e_commerce_app.ui.home.adapters.SearchAdapter
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
    private val searchAdapter by lazy { SearchAdapter() }
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchProduct()
        initObservers()
        categoryListAdapter.onClick = {
            homeViewModel.getCategoryProduct(it)
        }
        searchAdapter.onProductClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        saleProductsAdapter.onFavoriteClick = {
            if (it.isFavorite.not()) homeViewModel.addFavoriteProduct(it)
            else homeViewModel.deleteFavoriteProduct(it)
        }
        saleProductsAdapter.onProductClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        categoryProductAdapter.onProductClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }


    private fun initObservers() {
        with(binding) {
            with(homeViewModel) {
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
                            "All Products Error",
                            (it.toString()),
                            "Close", {}, "", {}, customView = null, false
                        )
                    }
                }

                searchProductLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        if (it != null) {
                            binding.rvCategory.visibility = View.GONE
                            binding.vpSaleProduct.visibility = View.GONE
                            binding.rvCategoryProduct.visibility = View.GONE
                            binding.rvSearchProduct.visibility = View.VISIBLE
                            it.products?.let { it1 ->
                                searchAdapter.updateList(it1)
                                rvSearchProduct.apply {
                                    setHasFixedSize(true)
                                    adapter = searchAdapter
                                }
                            }
                        }
                    }.doOnFailure {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
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
                            adapter = categoryListAdapter
                        }
                    }.doOnFailure {
                        managerAlertDialog.showAlertDialog(
                            "",
                            (it.message),
                            "Close", {}, "", {}, customView = null, false
                        )
                    }
                }
                categoryProductLiveData.observe(viewLifecycleOwner) {
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
                            "Category Product Error",
                            (it.message),
                            "Close", {}, "", {}, customView = null, false
                        )
                    }
                }
            }
        }
    }

    private fun searchProduct() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                homeViewModel.searchProduct(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNullOrEmpty()) {
                    binding.rvCategory.visibility = View.VISIBLE
                    binding.vpSaleProduct.visibility = View.VISIBLE
                    binding.rvCategoryProduct.visibility = View.VISIBLE
                    binding.rvSearchProduct.visibility = View.GONE
                } else {
                    homeViewModel.searchProduct(newText)
                }
                return false
            }
        })
    }
}