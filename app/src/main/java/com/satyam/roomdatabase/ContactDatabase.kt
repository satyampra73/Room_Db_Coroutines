package com.satyam.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Convertors::class)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var Instance: ContactDatabase? = null

        fun getDatabase(context: Context):ContactDatabase{
            if (Instance == null){
                synchronized(this){
                    Instance = Room.databaseBuilder(context.applicationContext,ContactDatabase::class.java,"contactDB").build()
                }

            }
            return  Instance!!
        }
    }

}