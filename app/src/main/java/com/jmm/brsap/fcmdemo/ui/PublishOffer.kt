package com.jmm.brsap.fcmdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.gson.JsonObject
import com.jmm.brsap.fcmdemo.databinding.FragmentPublishOfferBinding
import com.jmm.brsap.fcmdemo.model.Notification
import com.jmm.brsap.fcmdemo.model.Offer
import com.jmm.brsap.fcmdemo.util.BaseBottomSheetDialogFragment
import com.jmm.brsap.fcmdemo.util.Status
import com.jmm.brsap.fcmdemo.viewmodels.VendorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublishOffer : BaseBottomSheetDialogFragment<FragmentPublishOfferBinding>(FragmentPublishOfferBinding::inflate) {

    private val viewModel by activityViewModels<VendorViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val description = binding.etDescription.text.toString().trim()

            viewModel.postOffers(Offer(
                to = "/topics/offers",
                notification = Notification(
                    title = title,
                    body = description
                )
            ))


        }
    }

    override fun subscribeObservers() {
        viewModel.isSent.observe(viewLifecycleOwner, { _result ->
            when (_result.status) {
                Status.SUCCESS -> {
                    _result._data?.let {
                        showToast("Offer posted successfully !!!")
                        dismiss()
                    }
                    displayLoading(false)
                }
                Status.LOADING -> {
                    displayLoading(true)
                }
                Status.ERROR -> {
                    displayLoading(false)
                    _result.message?.let {
                        displayError(it)
                    }
                }
            }
        })
    }
}