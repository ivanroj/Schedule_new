package com.example.schedule_new.data

import com.google.gson.Gson
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

object DataManager {
    var json : String = ""
    var groupNumber:String = ""
    val gson = Gson()
    fun takeJsonFromServerByWeekNumber(weekNumber: Int){
        json = "{\"number\": 1, \"days\": [{\"name\": \"Пт\", \"date\": \"09.02.24\", \"lessons\": [{\"name\": \"Общая физика \", \"place\": \"ГУК Б-444\", \"teacher\": \"Побережский Сергей Юрьевич\", \"time\": \"18:15 – 19:45\", \"type\": \"ЛК\"}, {\"name\": \"Общая физика \", \"place\": \"ГУК Б-444\", \"teacher\": \"Побережский Сергей Юрьевич\", \"time\": \"20:00 – 21:30\", \"type\": \"ЛК\"}]},{\"name\": \"Вт\", \"date\": \"09.02.24\", \"lessons\": [{\"name\": \"Общая физика \", \"place\": \"ГУК Б-444\", \"teacher\": \"Побережский Сергей Юрьевич\", \"time\": \"18:15 – 19:45\", \"type\": \"ЛК\"}, {\"name\": \"Общая физика \", \"place\": \"ГУК Б-444\", \"teacher\": \"Побережский Сергей Юрьевич\", \"time\": \"20:00 – 21:30\", \"type\": \"ЛК\"}]}]}"
    }
    fun nonExamWeekObjectFromJsonString(weekNumber: Int): Week {
        takeJsonFromServerByWeekNumber(weekNumber)
        return gson.fromJson(json,Week::class.java)
    }
    fun examsFromJsonString(weekNumber: Int): Week{
        takeJsonFromServerByWeekNumber(weekNumber)
        return gson.fromJson(json,Week::class.java)
    }
    fun getWeeksNumberFromGroup(group:String) : Int{
        //запрос
        return 3
    }
    fun listOfWeek(number:Int): MutableList<String> {
        var start = 0
        var weekArray = generateSequence { start += 1; "Неделя " + start.toString()}.take(number+1).toMutableList()
        return weekArray
    }
    fun whatWeekNow(){
    }
}