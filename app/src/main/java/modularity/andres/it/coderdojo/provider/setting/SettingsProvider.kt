package modularity.andres.it.coderdojo.provider.setting

import android.app.Activity
import android.content.Context
import com.google.android.gms.maps.model.LatLng
import javax.inject.Singleton

/**
 * Created by Andres on 12/4/17.
 */

@Singleton
class SettingsProvider(val activity: Activity) {

    private val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    private fun saveDouble(value: Double, setting: SettingType) {
        editor.putString(setting.toString(), value.toString())
    }

    fun saveRange(value: Double) {
        saveDouble(value, SettingType.RANGE)
    }

    fun savePosition(value: LatLng) {
        saveDouble(value.latitude, SettingType.LAT)
        saveDouble(value.longitude, SettingType.LNG)
    }

    fun retrieveRange(): Double {
        return retrieveDouble(SettingType.RANGE).toDouble()
    }

    private fun retrieveDouble(value: SettingType): String {
        return sharedPreferences.getString(value.toString(), "-1.0")
    }

    fun retrievePosition(): LatLng {
        val lat = retrieveDouble(SettingType.LAT).toDouble()
        val lng = retrieveDouble(SettingType.LNG).toDouble()
        return LatLng(lat, lng)
    }


}