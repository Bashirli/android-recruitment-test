package com.bashirli.algoritmatask.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bashirli.algoritmatask.data.dto.local.InvestEntity

@Database(entities = [InvestEntity::class], version = 1)
abstract class LocalService : RoomDatabase() {
    abstract fun getDao(): InvestDAO
}