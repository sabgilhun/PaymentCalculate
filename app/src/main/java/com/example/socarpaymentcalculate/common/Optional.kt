package com.example.socarpaymentcalculate.common

class Optional<T>(
    private val optional: T?
) {
    fun isEmpty(): Boolean = optional == null

    fun isNotEmpty(): Boolean = optional != null

    fun get(): T {
        if (optional == null) {
            throw NullPointerException("No value present")
        } else {
            return optional
        }
    }
}