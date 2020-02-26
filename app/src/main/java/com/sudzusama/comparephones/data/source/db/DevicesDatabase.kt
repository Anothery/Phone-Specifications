package com.sudzusama.comparephones.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sudzusama.comparephones.data.model.ComparsionEntity
import com.sudzusama.comparephones.data.model.DeviceEntity

@Database(
    entities = [DeviceEntity::class, ComparsionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DevicesDatabase : RoomDatabase() {
    abstract fun devicesDao(): DevicesDao
    abstract fun comparsionsDao(): ComparsionsDao
}