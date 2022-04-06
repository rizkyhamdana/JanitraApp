package com.rizkyhamdana.janitraapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_products")
@Parcelize
data class Products (
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val desc: String,
    val category: String,
    val image: String
):Parcelable