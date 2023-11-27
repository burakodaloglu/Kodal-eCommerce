package com.burakkodaloglu.my_e_commerce_app.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.FragmentProfileBinding
import com.burakkodaloglu.my_e_commerce_app.util.common.ManagerDialog
import com.burakkodaloglu.my_e_commerce_app.util.common.viewBinding
import com.burakkodaloglu.my_e_commerce_app.util.storage.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val profileViewModel: ProfileViewModel by viewModels()
    private val managerAlertDialog by lazy { ManagerDialog(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        val userId = SharedPrefManager.getInstance(requireActivity()).data.userId
        profileViewModel.getUser(userId)
        binding.btnSignOut.setOnClickListener {
            SharedPrefManager.getInstance(requireActivity()).clear()
            findNavController().navigate(R.id.login_graph)
        }
    }

    private fun initObserver() {
        profileViewModel.userLiveData.observe(viewLifecycleOwner) {
            it.doOnSuccess {
                val email = it.user.email
                val name = it.user.name
                with(binding) {
                    tvEmailadres.text = email
                    tvUserName.setText(name)
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