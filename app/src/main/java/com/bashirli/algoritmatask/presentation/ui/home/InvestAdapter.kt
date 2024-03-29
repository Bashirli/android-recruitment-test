package com.bashirli.algoritmatask.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.algoritmatask.common.base.BaseAdapter
import com.bashirli.algoritmatask.databinding.ItemInvestBinding
import com.bashirli.algoritmatask.domain.model.InvestResultUiModel

class InvestAdapter : BaseAdapter<InvestResultUiModel>() {

    inner class InvestViewHolder(private val binding: ItemInvestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun find(item: InvestResultUiModel) {
            with(binding) {
                data = item

                executePendingBindings()
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