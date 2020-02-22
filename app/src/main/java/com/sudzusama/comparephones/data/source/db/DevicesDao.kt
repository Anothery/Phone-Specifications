package com.sudzusama.comparephones.data.source.db

import androidx.room.*
import com.sudzusama.comparephones.data.model.DeviceEntity
import io.reactivex.Single

@Dao
interface DevicesDao {
    @Query("SELECT * FROM device WHERE DeviceName LIKE '%' || :deviceName || '%'")
    fun getDevicesByTitle(deviceName: String): Single<List<DeviceEntity>>

    //TODO update it
    @Query("SELECT * FROM device LIMIT :amount")
    fun getLatestDevices(amount: Int) : Single<List<DeviceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevice(deviceEntity: DeviceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevices(devices : List<DeviceEntity>)

    @Delete
    fun deleteDevice(deviceEntity: DeviceEntity)

}