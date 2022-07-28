package com.example.nestedrecyclerview.extention

import com.example.nestedrecyclerview.data.HomeItem
import com.example.nestedrecyclerview.data.HomeItemType
import com.example.nestedrecyclerview.data.model.Post

fun Post.toHomeItem(): HomeItem<Any> {
    return (HomeItem(this, HomeItemType.TYPE_POST))
}

fun Post.convertNumberToMillionAndKiloString(number: Long): String {
    return when (number){
        in 1000000..Long.MAX_VALUE -> (((number / 1000000.0) * 10) / 10.0).toInt().toString() + "M"
        in 1000..999999 -> (((number / 1000.0) * 10.0) / 10.0).toInt().toString() + "K"
        else -> number.toString()
    }
}