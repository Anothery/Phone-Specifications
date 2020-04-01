package com.sudzusama.comparephones.data.source.db

import androidx.room.*
import com.sudzusama.comparephones.data.model.DeviceEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DevicesDao {
    @Query("SELECT * FROM device WHERE DeviceName LIKE '%' || :deviceName || '%'")
    fun getDevicesByTitle(deviceName: String): Single<List<DeviceEntity>>

    @Query("SELECT * FROM device WHERE lastUsed IS NOT NULL ORDER BY lastUsed desc LIMIT :amount")
    fun getLatestDevices(amount: Int): Single<List<DeviceEntity>>

    @Query("SELECT * FROM device WHERE DeviceName = :deviceName")
    fun getDeviceByName(deviceName: String): Single<DeviceEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDevice(deviceEntity: DeviceEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDevices(devices: List<DeviceEntity>)

    @Delete
    fun deleteDevice(deviceEntity: DeviceEntity)

    @Update
    fun updateDevice(deviceEntity: DeviceEntity): Completable
}