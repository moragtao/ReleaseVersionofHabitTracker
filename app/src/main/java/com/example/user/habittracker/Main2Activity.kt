package com.example.user.habittracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    val context = this

    val Calendar2RequestCode = 1
    var day:Int = 0
    var month:Int = 0
    var year:Int = 0
    val NotificationRequestCode = 2
    val ADD_TASK_REQUEST = 1
    var hour : Int = 8
    var min : Int = 0
    val cb = CatBaseHandler(context)
    companion object {
        val EXTRA_TASK_DESCRIPTION = "task"
    }
    var spindat : MutableList<String> = mutableListOf("Учеба","Работа","Саморазвитие")

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Calendar2RequestCode && resultCode == Activity.RESULT_OK && data != null)

        {
            if (data.getIntExtra("Day",0) != 0)
            {
                day = data.getIntExtra("Day", 0)
                month = data.getIntExtra("Month", 0)
                year = data.getIntExtra("Year", 0)
                val selectedDate = StringBuilder().append(data.getIntExtra("Day", 0).toString())
                        .append("-").append((data.getIntExtra("Month", 0) + 1).toString()).append("-").append(data.getIntExtra("Year", 0).toString())
                        .append(" ").toString()
                date.text = selectedDate

            }
        }
    }

    var dat = mutableListOf<String>()
    fun UpdateList(){
        var i : Int = 0
        dat.clear()
        var cbd = cb.ReadData()
        while(i <= cbd.lastIndex) {
            println("First huh?")
            if(cbd[i].name.isNotEmpty())
                dat.add(cbd[i].name)
            i++
            println("Huh what ${i}")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        UpdateList()

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        addnot.setOnClickListener {
            val intent = Intent(this, Notification::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }

        var a = 0
        var b = 0
        var c = 0

        button1.setOnClickListener{
            if (a != 0)
                a--
            freqcount.text = a.toString()
        }

        button2.setOnClickListener{
            if (a < 6)
                a++
            freqcount.text = a.toString()
        }

        date.setOnClickListener{
            val intent = Intent(this, Calendar2::class.java)

            startActivityForResult(intent, Calendar2RequestCode)

        }
        edittext.setOnClickListener {
            if (b == 0)
              edittext.setText("")
            b++
        }
        editques.setOnClickListener {
            if (c == 0)
                editques.setText("")
            c++
        }

        ok.setOnClickListener {
            val habit = edittext.text.toString()
            if (!habit.isEmpty() && edittext.text.isNotEmpty() && editques.text.isNotEmpty() && day != 0) {
                val res = Intent()
                res.putExtra(EXTRA_TASK_DESCRIPTION, habit)
                setResult(Activity.RESULT_OK, res)
                var habit = Habit(edittext.text.toString(),editques.text.toString(),spinner.selectedItem.toString(),freqcount.text.toString().toInt(),day,month,year,hour,min)
                var db = DataBaseHandler(context)
                db.insertData(habit)
                finish()
            }
            else
            {
                Toast.makeText(context,"Заполните все данные",Toast.LENGTH_SHORT)
                setResult(Activity.RESULT_CANCELED)
            }
        }
        var data = mutableListOf<String>("Учёба", "Работа", "Саморазвитие")
        var adapter = ArrayAdapter<String>(this, R.layout.my_spinner_item, data)
        spinner.adapter = adapter

        /* A void to connect and upload data to Date Base
        fun uploadOnDateBase(){
            if (edittext.text.toString().length > 0 && editques.text.toString().length > 0 && day != 0)
            {
                var habit = Habit(edittext.text.toString(),editques.text.toString(),spinner.selectedItem.toString(),freqcount.text.toString().toInt(),day,month,year,hour,min)
                var db = DataBaseHandler(context)
                db.insertData(habit)
            }
        }
        */
    }
}
