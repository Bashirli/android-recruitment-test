package com.bashirli.algoritmatask.presentation.ui.home

import android.graphics.Color
import android.graphics.DashPathEffect
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.algoritmatask.common.base.BaseAdapter
import com.bashirli.algoritmatask.databinding.ItemInvestBinding
import com.bashirli.algoritmatask.domain.model.InvestResultUiModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class InvestAdapter : BaseAdapter<InvestResultUiModel>() {

    inner class InvestViewHolder(private val binding: ItemInvestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun find(item: InvestResultUiModel) {
            with(binding) {
                data = item
                setChart(
                    listOf(
                        Entry(10f, item.valueOne.toFloatOrNull() ?: 0f),
                        Entry(40f, item.valueTwo.toFloatOrNull() ?: 0f),
                        Entry(70f, item.valueThree.toFloatOrNull() ?: 0f),
                        Entry(100f, item.valueFour.toFloatOrNull() ?: 0f),
                    )
                )
                executePendingBindings()
            }
        }

        private fun setChart(values: List<Entry>) {
            with(binding) {
                chart.setBackgroundColor(Color.TRANSPARENT)
                chart.description.isEnabled = false

                chart.setTouchEnabled(true)
                chart.setDragEnabled(true)
                chart.setScaleEnabled(true)
                chart.setPinchZoom(true)

                val set1: LineDataSet

                if (chart.data != null && chart.data.dataSetCount > 0) {
                    set1 = chart.data.getDataSetByIndex(0) as LineDataSet
                    set1.values = values
                    set1.notifyDataSetChanged()
                    chart.data.notifyDataChanged()
                    chart.notifyDataSetChanged()
                } else {
                    set1 = LineDataSet(values, "Price")

                    set1.setDrawIcons(false)
                    set1.enableDashedLine(10f, 0f, 0f)
                    set1.color = Color.BLACK
                    set1.setCircleColor(Color.BLACK)
                    set1.lineWidth = 1f
                    set1.circleRadius = 3f


                    // draw points as solid circles
                    set1.setDrawCircleHole(false)

                    // customize legend entry
                    set1.formLineWidth = 1f
                    set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
                    set1.formSize = 5f
                    set1.valueTextSize = 8f


                    set1.setFillColor(Color.BLACK)
                    val dataSets = ArrayList<ILineDataSet>()
                    dataSets.add(set1)
                    val data = LineData(dataSets)
                    chart.data = data
                }
            }
        }
    }


    override fun onCreate(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = InvestViewHolder(
        ItemInvestBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InvestViewHolder -> {
                items.getOrNull(position)?.let { item -> holder.find(item) }
            }
        }
    }
}