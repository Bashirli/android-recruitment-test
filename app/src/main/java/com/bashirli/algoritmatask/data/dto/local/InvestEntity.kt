package com.bashirli.algoritmatask.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InvestEntity(
    @ColumnInfo("status")
    val status: String?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("valueOne")
    val valueOne: String?,
    @ColumnInfo("valueTwo")
    val valueTwo: String?,
    @ColumnInfo("valueThree")
    val valueThree: String?,
    @ColumnInfo("valueFour")
    val valueFour: String?,
    @ColumnInfo("6")
    val x6: Int?,
    @ColumnInfo("date")
    val date: String?,
    @PrimaryKey(autoGenerate = true) val _id: Int? = null
)