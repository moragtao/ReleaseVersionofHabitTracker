package com.example.user.habittracker

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*
//import org.jetbrains.anko.db.classParser

class Settings : AppCompatActivity(){

    //var DB = MySqlHelper1.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
/*
        var num = 0

        var labels = DB.SelectLabel(this)
        var adapter = ArrayAdapter<String>(this, R.layout.my_listlabels_item, labels)
        labelslist.adapter = adapter
        addlabel.setOnClickListener {
            if (num < 15) {
                DB.AddLabel(this, edittext.text.toString())
                num++
                edittext.setText("")
                labels = DB.SelectLabel(this)
                adapter = ArrayAdapter<String>(applicationContext, R.layout.my_listlabels_item, labels)
                labelslist.adapter = adapter
                adapter.notifyDataSetChanged()
                println(DB)
            }
        }

        labelslist.setOnItemClickListener { parent, view, position, id ->
            val builder = AlertDialog.Builder(this)

            // Set the alert dialog title
            builder.setTitle("Удаление")

            // Display a message on alert dialog
            builder.setMessage("Удалить категорию?")

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Да") { dialog, which ->
                DB.DeleteLabel(this, labels[position])
                labels.removeAt(position)
                adapter = ArrayAdapter<String>(applicationContext, R.layout.my_listlabels_item, labels)
                labelslist.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            // Set a positive button and its click listener on alert dialog
            builder.setNegativeButton("Нет"){ dialog, which ->

            }

            builder.show()
        }

        save.setOnClickListener{
            finish()
        }
        */
    }
}