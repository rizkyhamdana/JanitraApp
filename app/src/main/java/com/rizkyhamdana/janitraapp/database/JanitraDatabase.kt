package com.rizkyhamdana.janitraapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Products::class,
    Checkout::class,
    Favorite::class,
    Orders::class],
    version = 1,
    exportSchema = false)
abstract class JanitraDatabase: RoomDatabase() {
    abstract fun janitraDao() : JanitraDao
    companion object{

        @Volatile
        private var INSTANCE: JanitraDatabase? = null

        fun getDatabase(context: Context): JanitraDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JanitraDatabase::class.java,
                    "janitra_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}