package com.sudzusama.comparephones.data.source.db

import androidx.room.*
import com.sudzusama.comparephones.data.model.Device
import io.reactivex.Single

@Dao
interface DevicesDao {
    @Query("SELECT * FROM device WHERE DeviceName LIKE :deviceName")
    fun getDevicesByTitle(deviceName: String): Single<List<Device>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevice(device: Device)

    @Delete
    fun deleteDevice(device: Device)

}