package com.example.schedule_new.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule_new.R
import com.example.schedule_new.data.Day
import com.example.schedule_new.databinding.DayElementBinding
import java.io.Console

class DayAdapter : RecyclerView.Adapter<DayAdapter.DayHolder>() {
    var dayList = ArrayList<Day>()
    class DayHolder(item:View) : RecyclerView.ViewHolder(item) {

        val binding = DayElementBinding.bind(item)

        fun bind(day : Day) = with(binding){
            val lessonAdapter = LessonAdapter()

            dayName.text = day.name


            rcLessonList.layoutManager = LinearLayoutManager(rcLessonList.context)
            rcLessonList.adapter = lessonAdapter
            lessonAdapter.newLessonsFromDay(day.lessons)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_element,parent,false)
        return  DayHolder(view)
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        holder.bind(dayList[position])
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    fun newDaysFromWeek(days: List<Day>){
        dayList = days as ArrayList<Day>
        notifyDataSetChanged()
    }


}