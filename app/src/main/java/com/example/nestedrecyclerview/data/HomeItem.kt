package com.example.nestedrecyclerview.data

data class HomeItem<T>(
    val item: T,
    val type: HomeItemType
) {
}