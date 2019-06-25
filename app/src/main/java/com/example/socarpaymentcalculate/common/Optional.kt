package com.example.socarpaymentcalculate.common

class Optional<T> constructor(
    private val data: T?,
    private val throwable: Throwable?
) {
    fun isEmpty(): Boolean = data == null

    fun isNotEmpty(): Boolean = data != null

    fun get(): T {
        if (data == null) {
            throw NullPointerException("No value present")
        } else {
            return data
        }
    }

    fun getError(): Throwable {
        if (throwable == null) {
            throw NullPointerException("No value present")
        } else {
            return throwable
        }
    }
}