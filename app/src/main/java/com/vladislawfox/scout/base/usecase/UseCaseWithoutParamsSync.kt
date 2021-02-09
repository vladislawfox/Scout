package com.vladislawfox.scout.base.usecase

interface UseCaseWithoutParamsSync<out T> {
    operator fun invoke(): T
}