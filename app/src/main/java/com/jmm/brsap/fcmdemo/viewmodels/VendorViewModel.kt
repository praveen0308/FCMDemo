package com.jmm.brsap.fcmdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.jmm.brsap.fcmdemo.model.FCMResponse
import com.jmm.brsap.fcmdemo.model.Offer
import com.jmm.brsap.fcmdemo.repositories.FCMRepository
import com.jmm.brsap.fcmdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorViewModel @Inject constructor(
    private val fcmRepository: FCMRepository
) :ViewModel(){

    private val _isSent = MutableLiveData<Resource<FCMResponse>>()
    val isSent: LiveData<Resource<FCMResponse>> = _isSent

    fun postOffers(offer:Offer) {

        viewModelScope.launch {

            fcmRepository
                .postOffer(offer)
                .onStart {
                    _isSent.postValue(Resource.Loading(true))
                }
                .catch { exception ->
                    exception.message?.let {
                        _isSent.postValue(Resource.Error(it))
                    }
                }
                .collect {
                    _isSent.postValue(Resource.Success(it))
                }
        }

    }
}