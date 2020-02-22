package com.sudzusama.comparephones.data.source.db

import androidx.room.*
import com.sudzusama.comparephones.data.model.Device
import io.reactivex.Single

@Dao
interface DevicesDao {
    @Query("SELECT * FROM device WHERE DeviceName LIKE :deviceName")
    fun getDevicesByTitle(deviceName: String): Single<List<Device>>

    @Query("SELECT * FROM device LIMIT :amount")
    fun getLatestDevices(amount: Int) : Single<List<Device>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevice(device: Device)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevices(devices : List<Device>)

    @Delete
    fun deleteDevice(device: Device)

}