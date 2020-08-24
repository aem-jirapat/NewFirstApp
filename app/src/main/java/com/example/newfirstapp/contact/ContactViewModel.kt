package com.example.newfirstapp.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.newfirstapp.database.Contact
import com.example.newfirstapp.database.ContactDAO
import kotlinx.coroutines.*

class ContactViewModel(val database: ContactDAO, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _contact = MutableLiveData<Contact?>()

    val startButtonVisible = Transformations.map(_contact) {
        null == it
    }

    private suspend fun insert(contact: Contact) {
        withContext(Dispatchers.IO) {
            database.insertName(contact)
        }
    }

}