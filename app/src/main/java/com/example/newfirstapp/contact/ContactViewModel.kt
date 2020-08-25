package com.example.newfirstapp.contact

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.newfirstapp.database.Contact
import com.example.newfirstapp.database.ContactDAO
import kotlinx.coroutines.*

class ContactViewModel(val database: ContactDAO, application: Application) :
    AndroidViewModel(application) {

    val inputName = MutableLiveData<String>()

    private var viewModelJob = Job()

    private val nights = database.getName()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var tonight = MutableLiveData<Contact?>()

    init{

    }

    private suspend fun getNameFromDatabase(): Contact? {
        return withContext(Dispatchers.IO) {
            var night = database.getName()
            night
        }
    }


    fun onStartTracking() {
        uiScope.launch {
            val newNight = Contact(0,"ERROR")
            insert(newNight)
            tonight.value = getNameFromDatabase()
        }
    }

    private suspend fun insert(night: Contact) {
        withContext(Dispatchers.IO) {
            database.insertName(night)
        }
    }

    val startButtonVisible = Transformations.map(tonight) {
        it == null
    }


}