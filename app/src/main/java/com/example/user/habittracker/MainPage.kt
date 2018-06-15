package com.example.user.habittracker

import android.app.*
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main_page.*
import android.app.PendingIntent
import android.media.RingtoneManager
import android.app.NotificationManager
import java.util.*


class MainPage : AppCompatActivity() {

    var context = this

    var num:Int = 0

    var dat : MutableList<String> = mutableListOf()

    val adapter by lazy {makeAdapter(dat)}

    val ADD_TASK_REQUEST = 1

    var current_id = -1

    var db = DataBaseHandler(context)

    fun UpdateList(){
        var i : Int = 0
        dat.clear()
        var hbd = db.readData()
        while(i <= hbd.lastIndex) {
            if(hbd[i].Hname.isNotEmpty())
                dat.add(hbd[i].Hname)
            i++
        }
        adapter.notifyDataSetChanged()
    }

    fun habitSelected(position: Int)
    {
        var hbd = db.readData()
        AlertDialog.Builder(this)
                .setTitle(dat[position])
                .setMessage("Вопрос: ${hbd[position].Hquestion}\nКатегория: ${hbd[position].Hcategory}\nЧастота: каждые ${hbd[position].Hfreq} дня\nДата начала: ${hbd[position].HstartDay}.${hbd[position].HstartMonth}.${hbd[position].HstartYear}\nВремя: ${hbd[position].HtimeHour} часов ${hbd[position].HtimeMin} минут\n")
                .setNegativeButton(R.string.Cancel,{
                    dialog, _ -> dialog.cancel()
                })
                .setPositiveButton(R.string.Status,
                        {
                            dialog, _ ->
                            AlertDialog.Builder(this)
                                    .setTitle("Изменить статус привычки")
                                    .setMessage("Вы можете изменить статус привычки ${dat[position]}")
                                    .setNeutralButton(R.string.Cancel,
                                            {
                                                dialog, _ -> dialog.cancel()
                                            })
                                    .setPositiveButton(R.string.Done,
                                            {
                                                dialog, _->
                                                dat[position] += " - Сделано"
                                                adapter.notifyDataSetChanged()
                                                dialog.cancel()
                                            })
                                    .setNegativeButton(R.string.Skip,
                                            {
                                                dialog, _ ->
                                                dat[position] += " - Пропуск"
                                                adapter.notifyDataSetChanged()
                                                dialog.cancel()
                                            })
                                    .create()
                                    .show()
                        })
                .setNeutralButton(R.string.Delete,
                        {
                            dialog, _ ->
                            db.DeleteSelected(dat[position])
                            UpdateList()
                            adapter.notifyDataSetChanged()
                            dialog.cancel()
                        })
                .create()
                .show()
    }
// Initial commit
    fun showNotification() {
    val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Title")
            .setWhen((Date(2018,6,15,3,13)).time)
            .setContentText("Notification text")

    val notification = builder.build()

    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(1, notification)
    /*var Not : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    Not.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
            1000 * 60 * 60 * 24, null);
            */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        statistic.imageAlpha = R.mipmap.settings_icon
        habitlist.adapter = adapter

        UpdateList()

        showNotification()

        registerForContextMenu(habitlist)

        del.setOnClickListener{
            db.deleteDataAll()
            dat.clear()
            adapter.notifyDataSetChanged()
        }

        setts.setOnClickListener {

        }

        habitlist.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            current_id = position
            habitSelected(position)
            //showNotification()
        }
        add.setOnClickListener {

            val intent = Intent(this, Main2Activity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }
        statistic.setOnClickListener {
            val intent = Intent(this, HabitTable::class.java)
            startActivity(intent)
        }

    }
    fun makeAdapter(list: List<String>): ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                val habit = data?.getStringExtra(Main2Activity.EXTRA_TASK_DESCRIPTION)
                habit?.let()
                {
                    dat.add(habit)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

}


