package com.example.user.habittracker

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext

val DATABASE_NAME = "RealDB"
val TABLE_NAME = "Habits"
val COL_ID = "id"
val COL_HNAME = "name"
val COL_HQUESTION = "question"
val COL_HCATEGORY = "category"
val COL_HFREQ = "frequency"
val COL_HSTARTDAY = "startDay"
val COL_HSTARTMONTH = "startMonth"
val COL_HSTARTYEAR = "startYear"
val COL_HTIMEHOUR = "timeHour"
val COL_HTIMEMIN = "timeMin"
val COL_HVIBRATE = "vibrate"


class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_HNAME + " VARCHAR(256)," + COL_HQUESTION + " VARCHAR(256)," + COL_HCATEGORY + " VARCHAR(256)," + COL_HFREQ + " INTEGER," + COL_HSTARTDAY + " INTEGER," + COL_HSTARTMONTH + " INTEGER," + COL_HSTARTYEAR + " INTEGER," + COL_HTIMEHOUR + " INTEGER," + COL_HTIMEMIN + " INTEGER," + COL_HVIBRATE + " BOOLEAN)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(habit: Habit){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_HNAME,habit.Hname)
        cv.put(COL_HQUESTION,habit.Hquestion)
        cv.put(COL_HCATEGORY,habit.Hcategory)
        cv.put(COL_HFREQ,habit.Hfreq)
        cv.put(COL_HSTARTDAY,habit.HstartDay)
        cv.put(COL_HSTARTMONTH,habit.HstartMonth)
        cv.put(COL_HSTARTYEAR,habit.HstartYear)
        cv.put(COL_HTIMEHOUR,habit.HtimeHour)
        cv.put(COL_HTIMEMIN,habit.HtimeMin)
        cv.put(COL_HVIBRATE,habit.Hvibrate)
        var result = db.insert(TABLE_NAME,null,cv)
        if (result == -1.toLong())
            Toast.makeText(context,"An error occured with datebase",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Данные успешно загружены",Toast.LENGTH_LONG).show()
    }

    fun readData() : MutableList<Habit> {
        var habits : MutableList<Habit> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        var result = db.rawQuery(query,null)

        if(result.moveToFirst()){
            do{
                var habit = Habit()
                habit.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                habit.Hname = result.getString(result.getColumnIndex(COL_HNAME))
                habit.Hquestion = result.getString(result.getColumnIndex(COL_HQUESTION))
                habit.Hcategory = result.getString(result.getColumnIndex(COL_HCATEGORY))
                habit.Hfreq = result.getString(result.getColumnIndex(COL_HFREQ)).toInt()
                habit.HstartDay = result.getString(result.getColumnIndex(COL_HSTARTDAY)).toInt()
                habit.HstartMonth = result.getString(result.getColumnIndex(COL_HSTARTMONTH)).toInt()
                habit.HstartYear = result.getString(result.getColumnIndex(COL_HSTARTYEAR)).toInt()
                habit.HtimeHour = result.getString(result.getColumnIndex(COL_HTIMEHOUR)).toInt()
                habit.HtimeMin = result.getString(result.getColumnIndex(COL_HTIMEMIN)).toInt()
                habit.Hvibrate = result.getString(result.getColumnIndex(COL_ID)).toBoolean()
                habits.add(habit)
            }while(result.moveToNext())
        }

        result.close()
        db.close()
        return habits
    }

    fun deleteDataAll(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,null,null)
        db.close()
    }

    fun DeleteSelected(name : String){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"name LIKE ?", arrayOf(name))
        db.close()
    }


}


