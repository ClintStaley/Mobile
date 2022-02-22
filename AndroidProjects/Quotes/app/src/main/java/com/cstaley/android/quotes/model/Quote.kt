package com.cstaley.android.quotes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Quote(@StringRes val txtResourceId: Int, @DrawableRes val imageResourceId: Int)
