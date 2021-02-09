package com.vladislawfox.scout.base.usecase

interface UseCaseSync<in P, out T> {
    operator fun invoke(params: P): T
}