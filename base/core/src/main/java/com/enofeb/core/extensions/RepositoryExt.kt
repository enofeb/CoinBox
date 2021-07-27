package com.enofeb.core.extensions

import com.enofeb.core.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

fun <T> Repository.knock(apiCall: suspend () -> T): Flow<T?> {
    return flow {
        val apiResult = apiCall.invoke()
        if (apiResult is Response<*>) {
            if (apiResult.code() >= 400) {
                throw HttpException(apiResult)
            } else {
                emit(apiResult)
            }
        } else {
            emit(apiResult)
        }
    }
}