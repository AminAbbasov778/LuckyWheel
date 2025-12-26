package com.example.wheel.model

import android.net.Uri

data class WheelModel(var img : Uri? = null, val name : String, val probability : Int = 0, val id : Int ) {
}