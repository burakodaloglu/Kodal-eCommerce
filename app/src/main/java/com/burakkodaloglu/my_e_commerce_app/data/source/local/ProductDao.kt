package com.burakkodaloglu.my_e_commerce_app.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.burakkodaloglu.my_e_commerce_app.data.model.Product


@Dao
interface ProductDao {

    @Query("SELECT * FROM favorite_products")
    suspend fun getFavoriteProduct(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProduct(productEntity: Product)

    @Delete
    suspend fun deleteFavoriteProduct(productEntity: Product)

}
