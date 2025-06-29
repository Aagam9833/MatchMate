package com.aagamshah.matchmate.common

import com.aagamshah.matchmate.data.utils.ErrorHandler

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: ErrorHandler) : Resource<Nothing>()
}