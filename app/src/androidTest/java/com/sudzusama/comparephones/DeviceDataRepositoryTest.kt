package com.sudzusama.comparephones

import android.util.Log
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.sudzusama.comparephones.data.model.mapper.ComparsionListMapper
import com.sudzusama.comparephones.data.model.mapper.DeviceListMapper
import com.sudzusama.comparephones.data.model.mapper.DeviceMapper
import com.sudzusama.comparephones.data.repository.DeviceDataRepository
import com.sudzusama.comparephones.data.source.db.DevicesDatabase
import com.sudzusama.comparephones.data.source.network.CPApiService
import com.sudzusama.comparephones.utils.FONO_API_URL
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class DeviceDataRepositoryTest {
    private lateinit var db: DevicesDatabase
    private lateinit var api: CPApiService
    private var repository: DeviceDataRepository

    init {
        initDatabase()
        initApi()
        repository = DeviceDataRepository(
            db,
            api,
            DeviceListMapper(DeviceMapper()),
            ComparsionListMapper(DeviceMapper())
        )
    }


    @Test
    fun shouldGetDevicesFromAPI() {
        val results = ArrayList<com.sudzusama.comparephones.data.model.Device>()
        api.getDevices("xiaomi")
            .subscribe(Consumer { results.addAll(it) })
        assert(results.size > 0)
    }

    @Test
    fun shouldAddDevicesToDatabase(){
        val results = ArrayList<com.sudzusama.comparephones.data.model.Device>()
        api.getDevices("xiaomi").subscribe()

        db.devicesDao().getDevicesByTitle("xiaomi")
            .subscribe(Consumer { results.addAll(it) })

        println(results.size.toString())
        Assert.assertTrue(results.size > 101)
    }

    private fun initDatabase() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            DevicesDatabase::class.java
        ).build()
    }

    private fun initApi() {
        val authInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("token", BuildConfig.THE_FONO_API_KEY)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder().addInterceptor(authInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(FONO_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create(CPApiService::class.java)
    }
}