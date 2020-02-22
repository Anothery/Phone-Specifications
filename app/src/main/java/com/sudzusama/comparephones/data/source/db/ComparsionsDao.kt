package com.sudzusama.comparephones.data.source.db

import androidx.room.*
import com.sudzusama.comparephones.data.model.ComparsionEntity
import com.sudzusama.comparephones.data.model.ComparsionWithDevices
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ComparsionsDao {
    @Transaction
    @Query("SELECT * FROM comparsion ORDER BY comparsionId DESC LIMIT :amount")
    fun getLatestComparsions(amount: Int): Single<List<ComparsionWithDevices>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComparsion(comparsionEntity: ComparsionEntity): Completable
}