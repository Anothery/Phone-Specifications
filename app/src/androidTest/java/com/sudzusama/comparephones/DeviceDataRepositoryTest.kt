package com.sudzusama.comparephones

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.sudzusama.comparephones.data.model.mapper.ComparsionListMapper
import com.sudzusama.comparephones.data.model.mapper.DeviceListMapper
import com.sudzusama.comparephones.data.model.mapper.DeviceMapper
import com.sudzusama.comparephones.data.repository.DeviceDataRepository
import com.sudzusama.comparephones.data.source.db.DevicesDatabase
import com.sudzusama.comparephones.data.source.network.CPApiService
import com.sudzusama.comparephones.utils.FONO_API_URL
import io.reactivex.subscribers.DisposableSubscriber
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class DeviceDataRepositoryTest {
    private val DEVICE_NAME = "xiaomi"
    private val WRONG_DEVICE_NAME = "Wrong Search Device Name $$$1123213214512"
    private lateinit var db: DevicesDatabase
    private lateinit var api: CPApiService
    private lateinit var repository: DeviceDataRepository

    @Before
    fun setUp(){
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
    fun getDevicesFromApi() {
        val mock = mock(DisposableSubscriber::class.java)

        api.getDevices(DEVICE_NAME).subscribe({ mock.onNext(any()) }, {})

        verify(mock).onNext(any())
    }


    @Test
    fun callsOnErrorIfDeviceNameIsWrong() {
        val mock = mock(DisposableSubscriber::class.java)

        api.getDevices(WRONG_DEVICE_NAME).subscribe({}, mock::onError)

        verify(mock).onError(any())
    }


    @Test
    fun addDevicesToDatabase() {
        val results = ArrayList<com.sudzusama.comparephones.data.model.Device>()

        repository.getDevices(DEVICE_NAME).subscribe()
        db.devicesDao().getDevicesByTitle(DEVICE_NAME).subscribe({results.addAll(it)}, {})

        Assert.assertTrue(results.size > 0)
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