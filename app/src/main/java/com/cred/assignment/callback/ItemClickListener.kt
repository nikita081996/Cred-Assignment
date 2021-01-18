package com.cred.assignment.callback

import android.view.View

interface ItemClickListener<T> {
    fun onRecyclerItemClicked(position:Int, view:View, data:T)
}