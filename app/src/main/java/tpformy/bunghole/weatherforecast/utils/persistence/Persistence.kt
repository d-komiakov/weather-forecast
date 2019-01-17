package tpformy.bunghole.weatherforecast.utils.persistence

import android.content.SharedPreferences

interface Persistence {
    fun putIntValues(values: Map<String, Int>)
    fun getIntValue(key: String): Int
}

class AndroidPersistence(private val sharedPreferences: SharedPreferences): Persistence {
    override fun putIntValues(values: Map<String, Int>) {
        val editor = sharedPreferences.edit()
        values.forEach { (key, value) -> editor.putInt(key, value) }
        editor.apply()
    }

    override fun getIntValue(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
}