package com.molchanov.core.data.geoposition

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class Geolocation @Inject constructor(
    private val context: Context
    ) {

    private lateinit var locationManager: LocationManager

    private val statusSubject: BehaviorSubject<String> =
        BehaviorSubject.create()

    fun getLocation(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        else {
            locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                1000F, locationListener
            )

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0F, locationListener
            )
            return true
        }
    }

    private val locationListener = LocationListener { location ->
        getAddress(location)
    }

    private fun getAddress(location: Location) {
        locationManager.removeUpdates(locationListener)
        val geocoder = Geocoder(context, Locale("ru_RU"))

        val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        if (address != null) {
            statusSubject.onNext("${address[0]?.locality}")
        }
    }

    fun getLocationObserver() = statusSubject
}