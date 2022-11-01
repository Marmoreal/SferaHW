package com.example.sferahw.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sferahw.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(

): ViewModel() {

    private val _userListLiveData = MutableLiveData<List<User>>()
    val userListLiveData: LiveData<List<User>> = _userListLiveData

    fun loadData(list: List<User>) {
        _userListLiveData.value = list
    }

    fun onClickSubscribe(id: Int) {
        val listOld = _userListLiveData.value.orEmpty()
        val listNew = listOld.toMutableList()
        val userIndex = listNew.indexOfFirst {
            it.id == id
        }
        _userListLiveData.value?.getOrNull(userIndex)?.let {
            listNew.set(userIndex, it.copy(isSubscribed = !it.isSubscribed))
        }
        _userListLiveData.value = listNew
    }
}