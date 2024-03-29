package com.bashirli.algoritmatask.data.service.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bashirli.algoritmatask.data.dto.local.InvestEntity

@Dao
interface InvestDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<InvestEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(data: List<InvestEntity>)

    @Query("SELECT (SELECT COUNT(*) FROM investentity) == 0")
    suspend fun isDatabaseEmpty(): Boolean

    @Query("select * from investentity")
    suspend fun getAll(): List<InvestEntity>

}