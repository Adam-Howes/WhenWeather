package com.example.whenweather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_snow.*
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {

    // Location Services
    private lateinit var locationManager: LocationManager
    private var hasGps = false
    private var hasNetwork = false
    private var locationGps: Location? = null
    private var locationNetwork: Location? = null

    // Latitude and longitude
    var lat: String = ""
    var lon: String = ""

    // API key for Open Weather Map
    val api: String = "e9d23847eaa8bbf92bdc4b7da10d59b0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_snow, R.id.navigation_rain, R.id.navigation_storm
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        getLocation()

        // TODO: Fix issue where api doesn't run if location permissions are asked for.
        weatherTask().execute()
    }

    inner class weatherTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String? {

            var response: String?

            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&units=metric&appid=$api").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }

            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            // TODO: Get working after fragment transitions
            try {
                /* JSON extraction from API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val currentWeather = weather.getString("description")
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val windSpeed =
                    wind.getString("speed") + "kph " + bearingToHeading(wind.getInt("deg"))
                val temp = main.getString("temp") + "°C"

                // Put data from JSON to text views
                current_weather_text_view.append(currentWeather)
                wind_snow_text_view.append(windSpeed)
                humidity_snow_text_view.append(humidity)
                pressure_snow_text_view.append(pressure)
                temperature_snow_text_view.append(temp)

            } catch (e: java.lang.Exception) {
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGps || hasNetwork) {

            if (hasGps) {
                Log.d("CodeAndroidLocation", "hasGps")
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    0F,
                    object : LocationListener {
                        override fun onLocationChanged(location: Location?) {
                            if (location != null) {
                                locationGps = location
                                lat = locationGps!!.latitude.toString()
                                lon = locationGps!!.longitude.toString()


                                Log.d(
                                    "CodeAndroidLocation",
                                    " GPS Latitude : " + locationGps!!.latitude
                                )
                                Log.d(
                                    "CodeAndroidLocation",
                                    " GPS Longitude : " + locationGps!!.longitude
                                )
                            }
                        }

                        override fun onStatusChanged(
                            provider: String?,
                            status: Int,
                            extras: Bundle?
                        ) {
                        }

                        override fun onProviderEnabled(provider: String?) {}

                        override fun onProviderDisabled(provider: String?) {}
                    })

                val localGpsLocation =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null)
                    locationGps = localGpsLocation
            }
            if (hasNetwork) {
                Log.d("CodeAndroidLocation", "hasGps")
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    10000,
                    0F,
                    object : LocationListener {
                        override fun onLocationChanged(location: Location?) {
                            if (location != null) {
                                locationNetwork = location
                                lat = locationNetwork!!.latitude.toString()
                                lon = locationNetwork!!.longitude.toString()

                                Log.d(
                                    "CodeAndroidLocation",
                                    " Network Latitude : " + locationNetwork!!.latitude
                                )
                                Log.d(
                                    "CodeAndroidLocation",
                                    " Network Longitude : " + locationNetwork!!.longitude
                                )
                            }
                        }

                        override fun onStatusChanged(
                            provider: String?,
                            status: Int,
                            extras: Bundle?
                        ) {
                        }

                        override fun onProviderEnabled(provider: String?) {}

                        override fun onProviderDisabled(provider: String?) {}
                    })

                val localNetworkLocation =
                    locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (localNetworkLocation != null)
                    locationNetwork = localNetworkLocation
            }

            if (locationGps != null && locationNetwork != null) {
                if (locationGps!!.accuracy > locationNetwork!!.accuracy) {

                    lat = locationNetwork!!.latitude.toString()
                    lon = locationNetwork!!.longitude.toString()

                    Log.d(
                        "CodeAndroidLocation",
                        " Network Latitude : " + locationNetwork!!.latitude
                    )
                    Log.d(
                        "CodeAndroidLocation",
                        " Network Longitude : " + locationNetwork!!.longitude
                    )
                } else {
                    lat = locationGps!!.latitude.toString()
                    lon = locationGps!!.longitude.toString()

                    Log.d("CodeAndroidLocation", " GPS Latitude : " + locationGps!!.latitude)
                    Log.d("CodeAndroidLocation", " GPS Longitude : " + locationGps!!.longitude)
                }
            }

        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    // Minimises app
    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    // Input compass degrees, returns string heading
    // API already has this feature, Redundant?
    fun bearingToHeading(bearing: Int): String {
        var heading = ""
        when (bearing) {
            in 337..360 -> heading = "North"
            in 0..23 -> heading = "North"
            in 24..68 -> heading = "North-East"
            in 69..113 -> heading = "East"
            in 114..158 -> heading = "South-East"
            in 159..203 -> heading = "South"
            in 204..248 -> heading = "South-West"
            in 249..293 -> heading = "West"
            in 294..336 -> heading = "North-West"
        }
        return heading
    }
}