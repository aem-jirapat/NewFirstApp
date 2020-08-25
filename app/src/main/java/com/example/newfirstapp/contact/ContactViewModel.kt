package com.example.newfirstapp.contact

import android.app.Application
import androidx.databinding.Bindable
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

    val inputName = MutableLiveData<String>()
    val addButton = MutableLiveData<String>()

    init{
        addButton.value = "Add"
    }

    fun addName(){
        val name = inputName.value!!
        insertName(Contact(0,name))
        inputName.value = null
    }

    fun insertName(contact: Contact) {
        uiScope.launch{
            database.insertName(contact)
        }
    }


}