package com.devundefined.menulistsample.infrastructure

sealed class Try<T> {
    companion object {
        operator fun <T> invoke(action: () -> T): Try<T> {
            return try {
                Success(action())
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }

    class Success<T>(val value: T) : Try<T>()
    class Failure<T>(val error: Throwable) : Try<T>()

    fun <R> map(transformAction: (T) -> R): Try<R> = when(this) {
        is Success<T> -> Success(transformAction(value))
        is Failure<T> -> Failure(error)
    }

    fun <R> flatMap(transformAction: (T) -> Try<R>): Try<R> = when(this) {
        is Success -> transformAction(value)
        is Failure -> Failure(error)
    }
}