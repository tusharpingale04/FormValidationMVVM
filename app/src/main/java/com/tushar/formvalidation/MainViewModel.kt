package com.tushar.formvalidation

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object{
        const val Username = 100
        const val Password = 200
    }

    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val event = SingleLiveEvent<Int>()
    val isValid = MediatorLiveData<Boolean>().apply {
        addSource(username){
            value = validateFields()
        }
        addSource(password){
            value = validateFields()
        }
    }

    private fun validateFields(): Boolean {
        if(username.value.isNullOrEmpty() || username.value!!.length < 6){
            event.value = Username
            return false
        }
        if(password.value.isNullOrEmpty()  || password.value!!.length < 6){
            event.value = Password
            return false
        }
        return true
    }
}