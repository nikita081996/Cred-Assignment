package com.cred.assignment.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Nikita Verma on 1/17/2021.
 */
@Parcelize
data class Plan(
    val month: Int = 12,
    val amount: String = "",
    val recommended: Boolean = false,
    var selected: Boolean = false,
    var colorRes: Int = 0
) : Parcelable