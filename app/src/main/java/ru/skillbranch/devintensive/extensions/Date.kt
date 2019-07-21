package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time
    time += when (units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.humanizeDiff(date:Date = Date()) : String {

    var timeDiff = date.time - this.time

    if (timeDiff >= 0) {
        if(timeDiff <= SECOND)
            return "только что"
        if (timeDiff <= 45 * SECOND)
            return "несколько секунд назад"
        if (timeDiff <= 75 * SECOND)
            return "минуту назад"
        if (timeDiff <= 45 * MINUTE) {
            val minutes = (timeDiff / MINUTE).toInt()
            when (minutes) {
                in 2..4, in 22..24, in 32..34, in 42..44 -> return "$minutes минуты назад"
                1, 21, 31, 41-> return "$minutes минуту назад"
                else -> return "$minutes минут назад"
            }
        }
        if (timeDiff <= 75 * MINUTE) {
            return "час назад"
        }
        if (timeDiff <= 22 * HOUR) {
            val hour = (timeDiff / HOUR).toInt()
            when (hour) {
                in 2..4, 22 -> return "$hour часа назад"
                1, 21 -> return "$hour час назад"
                else -> return "$hour часов назад"
            }
        }
        if (timeDiff <= 26 * HOUR) {
            return "день назад"
        }
        if (timeDiff <= 360 * DAY) {
            val day = (timeDiff / DAY).toInt()
            when (day % 100) {
                1, 21, 31, 41, 51, 61, 71, 81, 91 -> return "$day день назад"
                2,3,4,22,23,24,32,33,34,42,43,44,52,53,54,62,63,64,72,73,74,82,83,84,92,93,94 -> return "$day дня назад"
                else -> return "$day дней назад"
            }
        }
        //if (Date().time - date.time > 360 * DAY) {
            return "более года назад"
        //}
    }
    else {
        timeDiff = -timeDiff
        if(timeDiff <= SECOND)
            return "скоро"
        if (timeDiff <= 45 * SECOND)
            return "через несколько секунд"
        if (timeDiff <= 75 * SECOND)
            return "через минуту"
        if (timeDiff <= 45 * MINUTE) {
            val minutes = (timeDiff / MINUTE).toInt()
            when (minutes) {
                in 2..4, in 22..24, in 32..34, in 42..44 -> return "через $minutes минуты"
                1, 21, 31, 41-> return "через $minutes минуту"
                else -> return "через $minutes минут"
            }
        }
        if (timeDiff <= 75 * MINUTE) {
            return "через час"
        }
        if (timeDiff <= 22 * HOUR) {
            val hour = (timeDiff / HOUR).toInt()
            when (hour) {
                in 2..4, 22 -> return "черех $hour часа"
                1, 21 -> return "через $hour час"
                else -> return "через $hour часов"
            }
        }
        if (timeDiff <= 26 * HOUR) {
            return "через день"
        }
        if (timeDiff <= 360 * DAY) {
            val day = (timeDiff / DAY).toInt()
            when (day % 100) {
                1, 21, 31, 41, 51, 61, 71, 81, 91 -> return "через $day день"
                2,3,4,22,23,24,32,33,34,42,43,44,52,53,54,62,63,64,72,73,74,82,83,84,92,93,94 -> return "через $day дня"
                else -> return "через $day дней"
            }
        }
        return "более чем через год"
    }
}


