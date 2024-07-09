package com.example.schedule_new.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.schedule_new.R
import com.example.schedule_new.data.DataManager
import com.example.schedule_new.data.DayAdapter
import com.example.schedule_new.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment(),AdapterView.OnItemSelectedListener {

    private var _binding: FragmentScheduleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val dayAdapterForSchedule = DayAdapter()
    private var dayListForSchedule = DataManager.nonExamWeekObjectFromJsonString(0).days

    private var isFirstSelection = true // Флаг для отслеживания первого выбора в спиннере

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val scheduleViewModel =
            ViewModelProvider(this).get(ScheduleViewModel::class.java)

        _binding = FragmentScheduleBinding.inflate(layoutInflater)
        val root: View = binding.root

        val spinner: Spinner = binding.spinnerView
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter(
            this.requireContext(),
            R.layout.custom_list_item,
            DataManager.listOfWeek(5)
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }

        val textView: TextView = binding.textSchedule
        scheduleViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        initSchedule()
        }
        return root
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // Первый раз выбор сделан
        if (isFirstSelection) {
            isFirstSelection = false
            return // Ничего не делать, пока пользователь не выберет что-то в спиннере
        }

        // Выводить Toast только после первого выбора
        Toast.makeText(this.context, "Selected week:" + (pos+1).toString(), Toast.LENGTH_SHORT).show()

        // Обновление данных расписания
        _binding?.apply {
            dayListForSchedule = DataManager.nonExamWeekObjectFromJsonString(pos).days
            initSchedule()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Обработка, если ничего не выбрано, можно оставить пустым
    }

    private fun initSchedule() {
        _binding?.apply {
            rcViewShedule.layoutManager = LinearLayoutManager(this@ScheduleFragment.context)
            rcViewShedule.adapter = dayAdapterForSchedule
            dayAdapterForSchedule.newDaysFromWeek(dayListForSchedule)
        }
    }
}
