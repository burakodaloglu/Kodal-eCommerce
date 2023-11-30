package com.burakkodaloglu.my_e_commerce_app.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.burakkodaloglu.my_e_commerce_app.data.model.Product


@Database(entities = [Product::class], version = 2)
abstract class ProductRoomDB : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
