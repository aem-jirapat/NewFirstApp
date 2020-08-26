package com.example.newfirstapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDAO {
    @Insert
    fun insert(contact:Contact)

    @Query("SELECT * FROM contact_table")
    fun get(): LiveData<List<Contact>>
}