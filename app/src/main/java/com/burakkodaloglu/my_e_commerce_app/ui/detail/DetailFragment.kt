package com.burakkodaloglu.my_e_commerce_app.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.data.model.AddToCart
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentDetailBinding
import com.burakkodaloglu.my_e_commerce_app.ui.detail.adapters.DetailImagesAdapter
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import com.burakkodaloglu.my_e_commerce_app.util.storage.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val detailImagesAdapter by lazy { DetailImagesAdapter() }
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        val userId = SharedPrefManager.getInstance(requireActivity()).data.userId
        val cart = args.product
        with(binding) {
            imgFavorite.setOnClickListener { detailViewModel.setFavoriteState() }
            imgBack.setOnClickListener { findNavController().popBackStack() }
            btnAddCart.setOnClickListener {
                detailViewModel.addToCart(AddToCart(userId, cart.id))
            }
        }
    }

    private fun initObserver() {
        with(binding) {
            with(detailViewModel) {
                product.observe(viewLifecycleOwner) {
                    productModel = it
                    val imageList = listOf(it.imageOne, it.imageTwo, it.imageThree)
                    detailImagesAdapter.updateList(imageList)
                    val compositePageTransformer = CompositePageTransformer()
                    compositePageTransformer.addTransformer { page, position ->
                        val r = 1 - abs(position)
                        page.scaleY = (0.85f + r * 0.15f)
                    }
                    vpProductImg.apply {
                        adapter = detailImagesAdapter
                        clipToPadding = false
                        clipChildren = false
                        offscreenPageLimit = 3
                        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                        setPageTransformer(compositePageTransformer)
                        currentItem = 1
                    }
                }
                isFavorite.observe(viewLifecycleOwner) {
                    imgFavorite.setImageResource(
                        if (it) R.drawable.ic_favorite_selected
                        else R.drawable.ic_favorite_unselected
                    )
                }
                addCartLiveData.observe(viewLifecycleOwner) {
                    it.doOnSuccess {
                        Toast.makeText(
                            requireContext(),
                            "Product added to your cart",
                            Toast.LENGTH_LONG
                        ).show()
                    }.doOnFailure {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}