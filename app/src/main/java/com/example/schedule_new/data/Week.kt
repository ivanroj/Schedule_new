package com.example.schedule_new.data

data class Week(var number : Int,var days: List<Day>) {
    fun getDayByNumber(dayNumber: Int): Day {
        return days[dayNumber]
    }
}