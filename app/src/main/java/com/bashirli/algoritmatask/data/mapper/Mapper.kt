package com.bashirli.algoritmatask.data.mapper

import com.bashirli.algoritmatask.data.dto.remote.InvestDTO
import com.bashirli.algoritmatask.data.dto.remote.Result
import com.bashirli.algoritmatask.domain.model.InvestResultUiModel
import com.bashirli.algoritmatask.domain.model.InvestUiModel

fun Result?.toResultUiModel() = InvestResultUiModel(
    x0 = this?.x0.orEmpty(),
    x1 = this?.x1.orEmpty(),
    x2 = this?.x2.orEmpty(),
    x3 = this?.x3.orEmpty(),
    x4 = this?.x4.orEmpty(),
    x5 = this?.x5.orEmpty(),
    x6 = this?.x6 ?: -1,
    x7 = this?.x7.orEmpty(),
)

fun List<Result?>?.toListResultUiModel() = this?.mapNotNull {
    it.toResultUiModel()
}.orEmpty()

fun InvestDTO?.toInvestUiModel() = InvestUiModel(
    result = this?.result.toListResultUiModel(),
    total = this?.total ?: -1
)
