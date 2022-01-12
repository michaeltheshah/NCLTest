package com.example.ncltest.util.state

import okhttp3.Response

sealed class AwaitResult<out R> {
    data class Ok<out T>(val value: T, val response: Response): AwaitResult<T>()
    data class Error(val exception: Exception,
                     val response: Response? = null,
                     val jsonError: String? = null): AwaitResult<Nothing>()
}