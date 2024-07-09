package com.example.schedule_new.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule_new.R
import com.example.schedule_new.data.Lesson
import com.example.schedule_new.databinding.LessonElementBinding

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonHolder>(){
    var lessonList = ArrayList<Lesson>()
    class LessonHolder(item:View) : RecyclerView.ViewHolder(item) {
        private val binding = LessonElementBinding.bind(item)
        fun bind(lesson : Lesson) = with(binding){
            lessonName.text = lesson.name
            lessonTime.text = lesson.time
            lessonType.text = lesson.type
            lessonPlace.text = lesson.place
            lessonTeacher.text = lesson.teacher
        }
    }

    fun newLessonsFromDay(lessons: List<Lesson>){
        lessonList = lessons as ArrayList<Lesson>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_element,parent,false)
        return LessonHolder(view)
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    override fun onBindViewHolder(holder: LessonHolder, position: Int) {
        holder.bind(lessonList[position])
    }

}