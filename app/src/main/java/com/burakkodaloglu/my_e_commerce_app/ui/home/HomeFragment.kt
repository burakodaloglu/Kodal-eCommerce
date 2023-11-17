package com.burakkodaloglu.my_e_commerce_app.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentHomeBinding
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}