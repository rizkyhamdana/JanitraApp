package com.rizkyhamdana.janitraapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_favorite")
data class Favorite (
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    val category: String,
    val image: String
)