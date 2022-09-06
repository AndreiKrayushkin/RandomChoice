package com.example.randomchoisemvp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database
    (entities = [TextData::class], version = 1)

abstract class TextDatabase : RoomDatabase() {
    abstract fun getTextDao(): TextDao

    companion object{
        private var DATABASE: TextDatabase? = null
        fun getDatabase(context: Context): TextDatabase {
            if (DATABASE == null) {
                DATABASE = Room.databaseBuilder(context, TextDatabase::class.java, "database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return DATABASE as TextDatabase
        }
    }
}