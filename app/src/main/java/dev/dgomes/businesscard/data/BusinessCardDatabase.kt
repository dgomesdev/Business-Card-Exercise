package dev.dgomes.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusinessCard::class], version = 1)
abstract class BusinessCardDatabase : RoomDatabase() {

    abstract fun businessCardDao() : BusinessCardDao

    companion object {
        @Volatile
        private var INSTANCE: BusinessCardDatabase? = null

        fun getDatabase(context: Context): BusinessCardDatabase {
             return INSTANCE ?: synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     BusinessCardDatabase::class.java,
                     "businesscard_db"
                 ).build()
                 INSTANCE = instance
                 instance
             }
        }
    }

}