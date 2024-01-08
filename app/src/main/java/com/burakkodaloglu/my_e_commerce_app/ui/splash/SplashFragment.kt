package com.burakkodaloglu.my_e_commerce_app.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentSplashBinding
import com.burakkodaloglu.my_e_commerce_app.util.storage.SharedPrefManager
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            binding.tvAppName.apply {
                alpha = 0f
                animate().alpha(1f).duration = 2000
            }
            delay(2000)
            if (SharedPrefManager.getInstance(requireContext()).data.userId.isNullOrEmpty()) {
                findNavController().navigate(R.id.splashToSignIn)
            } else {
                Log.d("UserId", SharedPrefManager.getInstance(requireContext()).data.userId)
                findNavController().navigate(R.id.action_splashFragment_to_main_graph)
            }
        }

    }
}