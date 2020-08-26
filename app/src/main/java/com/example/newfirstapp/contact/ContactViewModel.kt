package com.example.newfirstapp.contact

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.newfirstapp.database.Contact
import com.example.newfirstapp.database.ContactDAO
import com.example.newfirstapp.databinding.FragmentContactBinding
import kotlinx.coroutines.*

class ContactViewModel(val database: ContactDAO,private val binding:FragmentContactBinding ,application: Application) :
    AndroidViewModel(application) {


}