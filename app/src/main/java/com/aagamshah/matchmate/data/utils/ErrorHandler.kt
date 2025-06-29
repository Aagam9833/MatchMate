package com.aagamshah.matchmate.data.utils

sealed class ErrorHandler(open val message: String) {

    data class NetworkError(override val message: String = "No internet connection") :
        ErrorHandler(message)

    data class ApiError(override val message: String) : ErrorHandler(message)
    data class DbError(override val message: String = "Database error") : ErrorHandler(message)
    data class UnknownError(override val message: String = "Something went wrong") :
        ErrorHandler(message)
}