package com.bashirli.algoritmatask.common.utils

import android.app.Activity
import android.view.View
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Activity.showMotionToast(
    title: String,
    message: String,
    style: MotionToastStyle = MotionToastStyle.INFO,
) {
    MotionToast.createColorToast(
        this,
        title,
        message,
        style,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold)
    )
}
