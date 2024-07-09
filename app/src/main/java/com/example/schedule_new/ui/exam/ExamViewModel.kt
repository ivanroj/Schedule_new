package com.example.schedule_new.ui.exam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExamViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is exam Fragment"
    }
    val text: LiveData<String> = _text
}