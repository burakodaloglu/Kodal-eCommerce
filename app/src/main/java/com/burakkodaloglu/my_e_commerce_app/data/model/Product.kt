package com.burakkodaloglu.my_e_commerce_app.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_products")
@Parcelize
data class Product(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "count")
    val count: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "image")
    val imageOne: String,

    @ColumnInfo(name = "image_two")
    val imageTwo: String,

    @ColumnInfo(name = "image_three")
    val imageThree: String,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "rate")
    val rate: Double,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "sale_state")
    val saleState: Boolean=true,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,

    @ColumnInfo(name = "salePrice")
    val salePrice: Double
) : Parcelable