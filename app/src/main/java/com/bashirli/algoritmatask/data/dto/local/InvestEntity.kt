package com.bashirli.algoritmatask.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InvestEntity(
    @ColumnInfo("0")
    val x0: String?,
    @ColumnInfo("1")
    val x1: String?,
    @ColumnInfo("2")
    val x2: String?,
    @ColumnInfo("3")
    val x3: String?,
    @ColumnInfo("4")
    val x4: String?,
    @ColumnInfo("5")
    val x5: String?,
    @ColumnInfo("6")
    val x6: Int?,
    @ColumnInfo("7")
    val x7: String?,
    @PrimaryKey(autoGenerate = true) val _id: Int? = null
)