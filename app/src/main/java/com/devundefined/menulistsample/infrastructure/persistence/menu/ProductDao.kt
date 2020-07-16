package com.devundefined.menulistsample.infrastructure.persistence.menu

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    fun findAll(): List<ProductEntity>

    @Insert
    fun saveAll(products: List<ProductEntity>)

    @Query("DELETE FROM ProductEntity")
    fun clear()
}