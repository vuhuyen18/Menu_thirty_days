package com.example.thirty_day.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Menu(
   @StringRes val nameMenu:Int,
   @DrawableRes val imageMenu:Int,
    @StringRes val nameDish:Int
)
