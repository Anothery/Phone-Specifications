package com.sudzusama.comparephones.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sudzusama.comparephones.data.model.Comparsion
import com.sudzusama.comparephones.data.model.ComparsionWithDevices
import io.reactivex.Single

@Dao
interface ComparsionsDao {
    @Query("SELECT * FROM comparsion ORDER BY comparsionId DESC LIMIT :amount")
    fun getLatestComparsions(amount : Int) : Single<List<ComparsionWithDevices>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComparsion(comparsion: Comparsion)
}