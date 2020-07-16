package com.devundefined.menulistsample.infrastructure.persistence.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CacheKeyEntryEntity(
    @PrimaryKey
    val cacheKey: String,
    val timeStamp: Long
)