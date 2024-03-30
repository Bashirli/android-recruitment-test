package com.bashirli.algoritmatask.data.mapper

import com.bashirli.algoritmatask.data.dto.local.InvestEntity
import com.bashirli.algoritmatask.data.dto.remote.InvestDTO
import com.bashirli.algoritmatask.data.dto.remote.Result
import com.bashirli.algoritmatask.domain.model.InvestResultUiModel
import com.bashirli.algoritmatask.domain.model.InvestUiModel

fun Result?.toResultUiModel() = InvestResultUiModel(
    status = this?.x0.orEmpty(),
    name = this?.x1.orEmpty(),
    valueOne = this?.x2.orEmpty(),
    valueTwo = this?.x3.orEmpty(),
    valueThree = this?.x4.orEmpty(),
    valueFour = this?.x5.orEmpty(),
    x6 = this?.x6 ?: -1,
    date = this?.x7.orEmpty(),
)

fun List<Result?>?.toListResultUiModel() = this?.mapNotNull {
    it.toResultUiModel()
}.orEmpty()

fun InvestDTO?.toInvestUiModel() = InvestUiModel(
    result = this?.result.toListResultUiModel(),
    total = this?.total ?: -1
)


fun InvestEntity.toInvestLocalUiModel() = InvestResultUiModel(
    status = status.orEmpty(),
    name = name.orEmpty(),
    valueOne = valueOne.orEmpty(),
    valueTwo = valueTwo.orEmpty(),
    valueThree = valueThree.orEmpty(),
    valueFour = valueFour.orEmpty(),
    x6 = x6 ?: -1,
    date = date.orEmpty(),
)

fun InvestResultUiModel.toInvestEntity(id: Int) = InvestEntity(
    status = status,
    name = name,
    valueOne = valueOne,
    valueTwo = valueTwo,
    valueThree = valueThree,
    valueFour = valueFour,
    x6 = x6,
    date = date,
    _id = id
)

fun List<InvestEntity>.toInvestListLocalUiModel() = map {
    it.toInvestLocalUiModel()
}

fun List<InvestResultUiModel>.toLocalDatabaseList() = mapIndexed { index, investResultUiModel ->
    investResultUiModel.toInvestEntity(index)
}

