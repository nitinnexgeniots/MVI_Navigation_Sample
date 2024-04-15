package com.nitin.core.extensions

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException

// Based on: https://proandroiddev.com/resilient-use-cases-with-kotlin-result-coroutines-and-annotations-511df10e2e16


inline fun <R> resultOf(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (t: TimeoutCancellationException) {
        Result.failure(t)
    } catch (c: CancellationException) {
        throw c
    } catch (e: Exception) {
        Result.failure(e)
    }
}
