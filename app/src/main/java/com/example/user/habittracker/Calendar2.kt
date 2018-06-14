package com.example.user.habittracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calendar2.*
import android.widget.CalendarView
import android.widget.Toast
import android.widget.CalendarView.OnDateChangeListener



class Calendar2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar2)
        clnd.setOnDateChangeListener { view, year, month, dayOfMonth ->
            /*val selectedDate = StringBuilder().append(month + 1)
                    .append("-").append(dayOfMonth).append("-").append(year)
                    .append(" ").toString()
            Toast.makeText(applicationContext, selectedDate, Toast.LENGTH_LONG).show()*/
            val intent = Intent()
            intent.putExtra("Year", year)
            intent.putExtra("Month", month)
            intent.putExtra("Day", dayOfMonth)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }




    }

}
