package com.example.schedule_new.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Schedule fragment"
    }
    val text: LiveData<String> = _text
}