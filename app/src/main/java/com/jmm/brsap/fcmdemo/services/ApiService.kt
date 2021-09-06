package com.jmm.brsap.fcmdemo.services


import com.google.gson.JsonObject
import com.jmm.brsap.fcmdemo.BuildConfig
import com.jmm.brsap.fcmdemo.model.FCMResponse
import com.jmm.brsap.fcmdemo.model.Offer
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers(
        "Authorization: key="+ BuildConfig.FCM_SERVER_KEY,
        "Content-Type: application/json"
    )
    @POST("fcm/send")
    suspend fun sendOffers(
        @Body offer: Offer
    ):FCMResponse


}