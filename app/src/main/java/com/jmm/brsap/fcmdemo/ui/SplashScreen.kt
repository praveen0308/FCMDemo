package com.jmm.brsap.fcmdemo.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jmm.brsap.fcmdemo.databinding.FragmentSplashScreenBinding
import com.jmm.brsap.fcmdemo.repositories.UserPreferencesRepository.Companion.LOGIN_DONE
import com.jmm.brsap.fcmdemo.repositories.UserPreferencesRepository.Companion.NEW_USER
import com.jmm.brsap.fcmdemo.util.BaseFragment
import com.jmm.brsap.fcmdemo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {

    private val viewModel by activityViewModels<MainViewModel>()
    private var userRoleID = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            performNavigation()
        }, 2000)

    }

    private fun performNavigation() {

        viewModel.userRoleID.observe(viewLifecycleOwner, {
            userRoleID = it
        })
        viewModel.welcomeStatus.observe(viewLifecycleOwner, {
            when (it) {
                NEW_USER -> findNavController().navigate(SplashScreenDirections.actionSplashScreenToLogin())
                LOGIN_DONE -> {
                    when (userRoleID) {
                        0 -> {
                            findNavController().navigate(SplashScreenDirections.actionSplashScreenToVendorActivity())
                            requireActivity().finish()
                        }
                        1 -> {
                            findNavController().navigate(SplashScreenDirections.actionSplashScreenToUserActivity())
                            requireActivity().finish()
                        }

                        else -> findNavController().navigate(SplashScreenDirections.actionSplashScreenToVendorActivity())
                    }


                }

            }

        })
    }

    override fun subscribeObservers() {

    }

}