package com.example.schedule_new.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.schedule_new.MainActivity
import com.example.schedule_new.StartActivity
import com.example.schedule_new.data.DataManager
import com.example.schedule_new.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val currentGroupText = binding.currentGroup
        val currentNightMode = resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK

        currentGroupText.setText(DataManager.groupNumber) // установка номера группы в окно

        binding.switchTheme.isChecked = currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        binding.buttonMaiSite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://mai.ru/")
            startActivity(intent)
        }
        binding.buttonReplaceGroup.setOnClickListener { // замена группы c сохранением
            val group = currentGroupText.text.toString()
            if(isItGroup(group)){
                replaceGroup(group)
                DataManager.groupNumber = group
            }else{
                currentGroupText.setText("Неверная группа")
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun replaceGroup(group:String){
        activity.let {(it as MainActivity).saveData(group)}
    }
    fun isItGroup(group:String) : Boolean{
        val patternRegexGroup = """[Мм][1-9иИсС][01234]?О-\d\d\d[А-Яа-я]-2\d"""
        val regex = Regex(patternRegexGroup)
        return regex.matches(group.trim())
    }
}
