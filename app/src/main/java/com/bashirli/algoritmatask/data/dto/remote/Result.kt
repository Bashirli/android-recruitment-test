package com.bashirli.algoritmatask.data.dto.remote


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("0")
    val x0: String?,
    @SerializedName("1")
    val x1: String?,
    @SerializedName("2")
    val x2: String?,
    @SerializedName("3")
    val x3: String?,
    @SerializedName("4")
    val x4: String?,
    @SerializedName("5")
    val x5: String?,
    @SerializedName("6")
    val x6: Int?,
    @SerializedName("7")
    val x7: String?
)