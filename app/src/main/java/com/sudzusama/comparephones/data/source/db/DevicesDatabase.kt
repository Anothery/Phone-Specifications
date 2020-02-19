package com.sudzusama.comparephones.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sudzusama.comparephones.data.model.Comparsion
import com.sudzusama.comparephones.data.model.Device

@Database(entities = [Device::class, Comparsion::class], version = 1)
abstract class DevicesDatabase : RoomDatabase() {
    abstract fun devicesDao(): DevicesDao
    abstract fun comparsionsDao(): ComparsionsDao
}