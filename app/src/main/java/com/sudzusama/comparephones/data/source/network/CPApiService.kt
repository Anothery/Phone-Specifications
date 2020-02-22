package com.sudzusama.comparephones.data.source.network

import com.sudzusama.comparephones.data.model.DeviceEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CPApiService {
    @GET("getdevice")
    fun getDevices(
        @Query("device") deviceName: String
    ): Single<List<DeviceEntity>>

}