package com.devundefined.menulistsample.infrastructure.cache

interface SystemTimeProvider {
    fun getSystemTime(): Long
}