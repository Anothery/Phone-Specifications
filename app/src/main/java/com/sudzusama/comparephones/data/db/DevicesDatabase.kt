package com.sudzusama.comparephones.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sudzusama.comparephones.domain.entities.Device

@Database(entities = [Device::class], version = 1)
abstract class DevicesDatabase : RoomDatabase() {
    abstract fun devicesDao(): DevicesDao
}