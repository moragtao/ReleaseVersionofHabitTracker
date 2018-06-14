package com.example.user.habittracker

import java.sql.Time

class Habit{

    var id : Int = 0
    var Hname : String = ""
    var Hquestion : String = ""
    var Hcategory : String = ""
    var Hfreq : Int = 0
    var HstartDay : Int = 15
    var HstartMonth : Int = 5
    var HstartYear : Int = 2018
    var HtimeHour : Int = 8
    var HtimeMin : Int = 0
    var Hvibrate : Boolean = false

    constructor(
            name: String,
            question : String,
            category : String,
            freq : Int,
            startDay : Int,
            startMonth : Int,
            startYear : Int,
            timeHour : Int,
            timeMin : Int,
            vibrate : Boolean = false
    ){
        this.Hname = name
        this.Hquestion = question
        this.Hcategory = category
        this.Hfreq = freq
        this.HstartDay = startDay
        this.HstartMonth = startMonth
        this.HstartYear = startYear
        this.HtimeHour = timeHour
        this.HtimeMin = timeMin
        this.Hvibrate = vibrate
    }
    constructor(){

    }
}