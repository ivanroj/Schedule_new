package com.example.schedule_new.ui.schedule

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
import com.example.schedule_new.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val dayAdapterForSchedule = DayAdapter()
    private var dayListForSchedule = DataManager.nonExamWeekObjectFromJsonString().days


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val scheduleViewModel =
            ViewModelProvider(this).get(ScheduleViewModel::class.java)

        _binding = FragmentScheduleBinding.inflate(layoutInflater)
        val root: View = binding.root

        val textView: TextView = binding.textSchedule
        scheduleViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
            initSchedule()
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initSchedule(){
        _binding?.apply {
            rcViewShedule.layoutManager = LinearLayoutManager(this@ScheduleFragment.context)
            rcViewShedule.adapter = dayAdapterForSchedule
            dayAdapterForSchedule.newDaysFromWeek(dayListForSchedule)
        }
    }
}