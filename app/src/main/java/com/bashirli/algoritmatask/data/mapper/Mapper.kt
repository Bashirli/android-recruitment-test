package com.bashirli.algoritmatask.data.mapper

import com.bashirli.algoritmatask.data.dto.local.InvestEntity
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


fun InvestEntity.toInvestLocalUiModel() = InvestResultUiModel(
    x0 = x0.orEmpty(),
    x1 = x1.orEmpty(),
    x2 = x2.orEmpty(),
    x3 = x3.orEmpty(),
    x4 = x4.orEmpty(),
    x5 = x5.orEmpty(),
    x6 = x6 ?: -1,
    x7 = x7.orEmpty(),
)

fun InvestResultUiModel.toInvestEntity(id: Int) = InvestEntity(
    x0 = x0,
    x1 = x1,
    x2 = x2,
    x3 = x3,
    x4 = x4,
    x5 = x5,
    x6 = x6,
    x7 = x7,
    _id = id
)

fun List<InvestEntity>.toInvestListLocalUiModel() = map {
    it.toInvestLocalUiModel()
}

fun List<InvestResultUiModel>.toLocalDatabaseList() = mapIndexed { index, investResultUiModel ->
    investResultUiModel.toInvestEntity(index)
}

