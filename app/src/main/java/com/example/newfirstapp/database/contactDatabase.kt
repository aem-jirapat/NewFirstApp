package com.example.newfirstapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [contact::class], version = 1, exportSchema = false)
abstract class contactDatabase : RoomDatabase() {

    abstract val contactDao: contactDAO

    companion object {

        @Volatile
        private var INSTANCE: contactDatabase? = null

        fun getInstance(context: Context): contactDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        contactDatabase::class.java,
                        "contact_data_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}