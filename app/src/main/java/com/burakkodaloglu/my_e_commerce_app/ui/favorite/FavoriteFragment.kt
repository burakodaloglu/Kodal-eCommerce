package com.burakkodaloglu.my_e_commerce_app.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentFavoriteBinding
import com.burakkodaloglu.my_e_commerce_app.ui.favorite.adapters.FavoritesAdapter
import com.burakkodaloglu.my_e_commerce_app.util.common.ManagerDialog
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val managerAlertDialog by lazy { ManagerDialog(requireContext()) }
    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val favoritesViewModel: FavoriteViewModel by viewModels()
    private val favoritesAdapter by lazy { FavoritesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        favoritesAdapter.onDeleteClick = {
            favoritesViewModel.deleteProductFavorite(it)
        }
    }

    fun initObserver() {
        with(binding) {
            with(favoritesViewModel) {
                getFavoriteLiveData.observe(viewLifecycleOwner) {
                    if (it != null) {
                        favoritesAdapter.apply {
                            updateList(it)
                            rvFavorites.setHasFixedSize(true)
                            rvFavorites.adapter = favoritesAdapter
                        }

                    } else {
                        managerAlertDialog.showAlertDialog(
                            "",
                            "Error",
                            "Close", {}, "", {}, customView = null, false
                        )
                    }
                }
                deleteProductFavorites.observe(viewLifecycleOwner) {
                    if (it != null) {
                        Toast.makeText(requireContext(), "Product Deleted", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(requireContext(), "Product didn't delete", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
}