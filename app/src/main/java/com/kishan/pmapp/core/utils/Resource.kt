package com.kishan.pmapp.core.utils

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val uiText: UiText? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(uiText: UiText? = null, data: T? = null) : Resource<T>(data,uiText)
}