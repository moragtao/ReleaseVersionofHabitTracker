package com.example.user.habittracker

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*
import kotlin.collections.ArrayList

val DATABASE_NAME2 = "CatBase"
val TABLE_NAME2 = "Cats"
val COL_ID2 = "id"
val COL_CAT = "cat"

class CatBaseHandler(var context: android.content.Context) : SQLiteOpenHelper(context, DATABASE_NAME2,null,2){
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME2 + "(" + COL_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_CAT + " VARCHAR(256))";

        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun InsertData(cat: Cat){
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_CAT,cat.name)
        var result = db.insert(TABLE_NAME2,null,cv)
    }

    fun ReadData() : MutableList<Cat> {
        var cats: MutableList<Cat> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME2
        var result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var cat = Cat()
                cat.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                cat.name = result.getString(result.getColumnIndex(COL_HNAME))
                cats.add(cat)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return cats
    }

    fun DeleteDataAll(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME2,null,null)
        db.close()
    }

    fun DeleteSelected(cat : String){
        val db = this.writableDatabase
        db.delete(TABLE_NAME2,"cat LIKE ?", arrayOf(cat))
        db.close()
    }
}
