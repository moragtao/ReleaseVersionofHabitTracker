package com.example.user.habittracker

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.user.habittracker.R
import kotlinx.android.synthetic.main.activity_notification.*


class Notification : AppCompatActivity() {

    companion object {
        val EXTRA_TASK_DESCRIPTION = "Time"
    }

    val ADD_TASK_REQUEST : Int = 1

    var tm = "Не выбрано"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        savetime.setOnClickListener{
            tm = "${timePicker.hour}:${timePicker.minute}"
            if (tm != null) {
                val res = Intent()
                res.putExtra(Notification.EXTRA_TASK_DESCRIPTION, tm)
                setResult(Activity.RESULT_OK, res)
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
    }
}

