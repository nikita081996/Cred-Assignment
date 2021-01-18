package com.cred.assignment.binding

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.cred.assignment.AppController

@BindingAdapter("colorIntValue")
fun setBackgroundColor(view: ConstraintLayout, colorId: Int) {
    view.setBackgroundTintList(ContextCompat.getColorStateList(AppController.instance, colorId));
}
