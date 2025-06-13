package com.restable.network.util

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import io.github.jan.supabase.exceptions.RestException
import kotlinx.coroutines.ensureActive
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <T> safeSupabaseCall(
    crossinline call: suspend () -> T
): Result<T, DataError.NetworkError> {
    return try {
        Result.Success(call())
    } catch (e: RestException) {
        Result.Error(DataError.NetworkError.SUPABASE_ERROR)
    } catch (e: SocketTimeoutException) {
        Result.Error(DataError.NetworkError.REQUEST_TIMEOUT)
    } catch (e: UnknownHostException) {
        Result.Error(DataError.NetworkError.NO_INTERNET)
    } catch (e: UnresolvedAddressException) {
        Result.Error(DataError.NetworkError.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        Result.Error(DataError.NetworkError.UNKNOWN)
    }
}
