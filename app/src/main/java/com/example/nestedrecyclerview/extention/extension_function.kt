package com.example.nestedrecyclerview.extention

import com.example.nestedrecyclerview.data.HomeItem
import com.example.nestedrecyclerview.data.HomeItemType
import com.example.nestedrecyclerview.data.model.Post

fun Post.toHomeItem(): HomeItem<Any> {
    return (HomeItem(this, HomeItemType.TYPE_POST))
}