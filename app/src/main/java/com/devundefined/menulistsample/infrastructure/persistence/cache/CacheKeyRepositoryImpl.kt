package com.devundefined.menulistsample.infrastructure.persistence.cache

import com.devundefined.menulistsample.infrastructure.cache.CacheKeyEntry
import com.devundefined.menulistsample.infrastructure.cache.CacheKeyRepository

class CacheKeyRepositoryImpl(private val dao: CacheKeyDao) : CacheKeyRepository {

    private val toModel: (CacheKeyEntryEntity) -> CacheKeyEntry = { entity ->
        CacheKeyEntry(entity.cacheKey, entity.timeStamp)
    }

    private val toEntity: (CacheKeyEntry) -> CacheKeyEntryEntity = { model ->
        CacheKeyEntryEntity(model.cacheKey, model.cacheTimeStamp)
    }

    override fun findByCacheKey(cacheKey: String): CacheKeyEntry? = dao.findByCacheKey(cacheKey)?.let(toModel)

    override fun save(cacheKeyEntry: CacheKeyEntry) {
        dao.removeByCacheKey(cacheKeyEntry.cacheKey)
        dao.insert(cacheKeyEntry.let(toEntity))
    }
}