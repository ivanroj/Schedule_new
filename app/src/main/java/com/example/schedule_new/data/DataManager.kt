package com.example.schedule_new.data

import com.google.gson.Gson

object DataManager {
    var json : String = ""
    var groupNumber = null
    val gson = Gson()
    fun takeJsonFromServer(){
        json = "{\"number\": 1, \"days\": [{\"name\": \"Пт\", \"date\": \"09.02.24\", \"lessons\": [{\"name\": \"Общая физика \", \"place\": \"ГУК Б-444\", \"teacher\": \"Побережский Сергей Юрьевич\", \"time\": \"18:15 – 19:45\", \"type\": \"ЛК\"}, {\"name\": \"Общая физика \", \"place\": \"ГУК Б-444\", \"teacher\": \"Побережский Сергей Юрьевич\", \"time\": \"20:00 – 21:30\", \"type\": \"ЛК\"}]},{\"name\": \"Вт\", \"date\": \"09.02.24\", \"lessons\": [{\"name\": \"Общая физика \", \"place\": \"ГУК Б-444\", \"teacher\": \"Побережский Сергей Юрьевич\", \"time\": \"18:15 – 19:45\", \"type\": \"ЛК\"}, {\"name\": \"Общая физика \", \"place\": \"ГУК Б-444\", \"teacher\": \"Побережский Сергей Юрьевич\", \"time\": \"20:00 – 21:30\", \"type\": \"ЛК\"}]}]}"

    }
    fun nonExamWeekObjectFromJsonString(): Week {
        takeJsonFromServer()
        return gson.fromJson(json,Week::class.java)
    }
    fun examsFromJsonString(): Week{
        takeJsonFromServer()
        return gson.fromJson(json,Week::class.java)
    }
}