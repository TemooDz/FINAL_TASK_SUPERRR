package com.forfinal.SUPER.ui.utils

sealed class CommonRequestResult {
    data class OnSuccess(val result: String) : CommonRequestResult()
    object OnError : CommonRequestResult()
}
