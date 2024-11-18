package com.vordead.snoozeloo.core.data.local

import com.vordead.snoozeloo.core.domain.util.DataError
import com.vordead.snoozeloo.core.domain.util.Result
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeLocalCall(
    execute: () -> suspend () -> T
): Result<T, DataError.Local> {
    return try {
        val result = execute().invoke()
        Result.Success(result)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        Result.Error(DataError.Local.DISK_FULL)
    }
}