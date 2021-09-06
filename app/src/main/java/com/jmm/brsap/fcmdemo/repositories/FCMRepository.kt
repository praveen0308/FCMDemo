package com.jmm.brsap.fcmdemo.repositories

import com.google.gson.JsonObject
import com.jmm.brsap.fcmdemo.model.FCMResponse
import com.jmm.brsap.fcmdemo.model.Offer
import com.jmm.brsap.fcmdemo.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class FCMRepository @Inject constructor(
    private val apiService: ApiService
){
    suspend fun postOffer(offer: Offer): Flow<FCMResponse> {
        return flow {
            val response = apiService.sendOffers(offer)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

}