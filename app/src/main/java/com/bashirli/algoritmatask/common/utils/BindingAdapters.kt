package com.bashirli.algoritmatask.common.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bashirli.algoritmatask.R

object BindingAdapters {

    @BindingAdapter("setColor")
    @JvmStatic
    fun setColor(imageView: ImageView, data: String) {
        if (data == "up") {
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.green))
        } else {
            imageView.setColorFilter(ContextCompat.getColor(imageView.context, R.color.red))
        }
    }

}