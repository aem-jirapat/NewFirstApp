package com.example.newfirstapp.contact

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newfirstapp.database.ContactDAO
import com.example.newfirstapp.databinding.FragmentContactBinding
import java.lang.IllegalArgumentException

class ContactViewModelFactory(
    private val dataSource: ContactDAO,
    private val binding: FragmentContactBinding,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)){
            return ContactViewModel(dataSource,binding,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}