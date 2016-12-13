package be.neodigi.kotlinboilerplate.data.local

import android.content.Context
import android.content.SharedPreferences
import be.neodigi.kotlinboilerplate.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(@ApplicationContext context: Context) {
    val PREF_FILE_NAME = "android_kotlinboilerplate_pref_file"
    private var pref: SharedPreferences
    init {
        pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        pref.edit().clear().apply()
    }
}