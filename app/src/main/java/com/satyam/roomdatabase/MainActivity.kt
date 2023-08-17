package com.satyam.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.View
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //can view database details on App Inspection - DatabaseInspector
        database=Room.databaseBuilder(applicationContext,ContactDatabase::class.java,"contactDB").build()
        GlobalScope.launch {
        database.contactDao().insertContact(Contact(0,"Satyam","6565656565"))
        }

    }

    //here on click on text you can see stored data ihn log\
    fun getdata(view : View){
        database.contactDao().getContact().observe(this,{
            Log.d("mydata",it.toString())
        })
    }
}