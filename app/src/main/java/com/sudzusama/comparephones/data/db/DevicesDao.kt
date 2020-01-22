package com.sudzusama.comparephones.data.db

import androidx.room.*
import com.sudzusama.comparephones.domain.entities.Device
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