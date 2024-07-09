package com.example.schedule_new.ui.exam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule_new.data.DataManager
import com.example.schedule_new.data.DayAdapter
import com.example.schedule_new.databinding.FragmentExamBinding

class ExamFragment : Fragment() {

    private var _binding: FragmentExamBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val dayAdapterForExams = DayAdapter()
    private var dayListForExams = DataManager.examsFromJsonString().days

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val examViewModel =
            ViewModelProvider(this).get(ExamViewModel::class.java)

        _binding = FragmentExamBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textExam
        examViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
            initExam()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initExam(){
        _binding?.apply {
            rcExamView.layoutManager = LinearLayoutManager(this@ExamFragment.context)
            rcExamView.adapter = dayAdapterForExams
            dayAdapterForExams.newDaysFromWeek(dayListForExams)
        }
    }
}