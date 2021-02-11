package com.vladislawfox.scout.base.data

data class Page<T>(
    val page: Int,
    val totalPage: Int,
    val items: List<T>
)
