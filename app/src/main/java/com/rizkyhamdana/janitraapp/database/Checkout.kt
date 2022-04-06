package com.rizkyhamdana.janitraapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_checkout")
data class Checkout (
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val quantity: Int,
    val total: Int,
)