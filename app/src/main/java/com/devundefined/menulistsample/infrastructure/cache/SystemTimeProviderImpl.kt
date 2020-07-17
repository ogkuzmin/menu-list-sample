package com.devundefined.menulistsample.infrastructure.cache

class SystemTimeProviderImpl : SystemTimeProvider {

    override fun getSystemTime(): Long = System.currentTimeMillis()
}