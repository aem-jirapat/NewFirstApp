package com.example.newfirstapp.contact

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.newfirstapp.database.Contact
import com.example.newfirstapp.database.ContactDAO
import com.example.newfirstapp.databinding.FragmentContactBinding
import kotlinx.coroutines.*

class ContactViewModel(
    val database: ContactDAO,
    private val binding: FragmentContactBinding,
    application: Application
) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val contacts = database.get()
    val contactString = Transformations.map(contacts) { contacts ->
        formatContact(contacts)
    }

    private fun formatContact(contact: List<Contact>): Spanned {
        val sb = StringBuilder()
        sb.apply {
            //append(resources.getString(R.string.title))
            contact.forEach {
                append(it.id)
                append(" : ")
                append(it.name)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onContactAdd() {
        uiScope.launch {
            val newContact = Contact()
            newContact.name = binding.editText.text.toString()
            insert(newContact)
        }
    }

    private suspend fun insert(contact: Contact) {
        withContext(Dispatchers.IO) {
            database.insert(contact)
        }
    }

}

