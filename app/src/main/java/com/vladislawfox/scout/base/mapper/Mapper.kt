package com.vladislawfox.scout.base.mapper

interface Mapper<F, T> {
    fun map(from: F): T

    fun map(from: List<F>): List<T> {
        return MutableList(from.size) {
            map(from[it])
        }
    }
}