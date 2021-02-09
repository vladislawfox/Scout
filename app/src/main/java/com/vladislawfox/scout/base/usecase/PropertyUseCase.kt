package com.vladislawfox.scout.base.usecase

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PropertyUseCase<T> {
    fun set(value: T)
    fun get(): T

    operator fun invoke(): ReadWriteProperty<Any, T> = PropertyDelegate(this)
}

private class PropertyDelegate<T>(
    private val useCase: PropertyUseCase<T>
) : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return useCase.get()
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        useCase.set(value)
    }
}