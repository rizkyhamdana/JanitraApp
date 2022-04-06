package com.rizkyhamdana.janitraapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_orders")
data class Orders (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val date: String,
    val name: String,
    val whatsapp: String,
    val address: String
)