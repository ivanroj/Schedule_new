package com.example.schedule_new.data

data class Day(var name : String,var date: String, var lessons: List<Lesson>){
    fun getLessonByNumber(lessonNumber: Int): Lesson {
        return lessons[lessonNumber]
    }
}