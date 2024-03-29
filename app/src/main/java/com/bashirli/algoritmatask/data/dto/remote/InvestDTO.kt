package com.bashirli.algoritmatask.data.dto.remote


import com.google.gson.annotations.SerializedName

data class InvestDTO(
    @SerializedName("result")
    val result: List<Result?>?,
    @SerializedName("total")
    val total: Int?
)