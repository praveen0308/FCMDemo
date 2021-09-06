package com.jmm.brsap.fcmdemo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.jmm.brsap.fcmdemo.R
import com.jmm.brsap.fcmdemo.databinding.FragmentLoginBinding
import com.jmm.brsap.fcmdemo.repositories.UserPreferencesRepository.Companion.LOGIN_DONE
import com.jmm.brsap.fcmdemo.util.BaseFragment
import com.jmm.brsap.fcmdemo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Login : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private var userType = 0

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noOfPlayers = arrayOf("Vendor","User")
        val arrayAdapter = ArrayAdapter(requireContext(),
            R.layout.support_simple_spinner_dropdown_item,noOfPlayers)

        binding.ddlUserType.setAdapter(arrayAdapter)

        binding.ddlUserType.setOnItemClickListener { parent, view, position, id ->
            userType = position
        }
        binding.btnLogin.setOnClickListener {
            viewModel.updateWelcomeStatus(LOGIN_DONE)
            if (userType==0){
                viewModel.updateUserRoleID(0)
                val intent = Intent(requireActivity(), VendorActivity::class.java)
                startActivity(intent)
            }else{
                viewModel.updateUserRoleID(1)
                val intent = Intent(requireActivity(), UserActivity::class.java)
                startActivity(intent)
            }

        }


    }
    override fun subscribeObservers() {

    }

}
