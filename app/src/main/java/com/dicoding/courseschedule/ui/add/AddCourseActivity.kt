package com.dicoding.courseschedule.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {
    private lateinit var addCourseViewModel: AddCourseViewModel
    private lateinit var tvStart: TextView
    private lateinit var tvEnd: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        tvStart = findViewById(R.id.add_tv_startTime)
        tvEnd = findViewById(R.id.add_tv_endTime)

        val factory = AddCourseViewModelFactory.createFactory(this)
        addCourseViewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]

        addCourseViewModel.saved.observe(this){
            if (it.getContentIfNotHandled() == true){
                finish()
            } else {
                Toast.makeText(this, getString(R.string.input_empty_message), Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_insert -> {
                val courseName = findViewById<TextInputEditText>(R.id.ed_course_name).text.toString()
                val day = findViewById<Spinner>(R.id.spinner_day).selectedItemPosition
                val start = tvStart.text.toString()
                val end = tvEnd.text.toString()
                val lecturer = findViewById<TextInputEditText>(R.id.ed_lecturer).text.toString()
                val notes = findViewById<TextInputEditText>(R.id.ed_note).text.toString()

                addCourseViewModel.insertCourse(courseName, day, start, end, lecturer, notes)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setShowTimePicker(view: View){
        val dialog = TimePickerFragment()
        val tag = when(view.id){
            R.id.ib_start_time -> "startTime"
            R.id.ib_end_time -> "endTime"
            else -> ""
        }
        dialog.show(supportFragmentManager, tag)
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val time = dateFormat.format(calendar.time)
        when(tag) {
            "startTime" -> {
                tvStart.text = time
            }
            "endTime" -> {
                tvEnd.text = time
            }
        }
    }
}
