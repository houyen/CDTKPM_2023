package com.cdtkpm_2023.driverapp.helper

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationRequest
import com.spartons.driverapp.R
import com.spartons.driverapp.interfaces.IPositiveNegativeListener


class GoogleMapHelper {

    companion object {
        private const val ZOOM_LEVEL = 18
        private const val TILT_LEVEL = 25
    }

    /**
     * @param latLng in which position to Zoom the camera.
     * @return the [CameraUpdate] with Zoom and Tilt level added with the given position.
     */

    fun buildCameraUpdate(latLng: LatLng): CameraUpdate {
        val cameraPosition = CameraPosition.Builder()
                .target(latLng)
                .tilt(TILT_LEVEL.toFloat())
                .zoom(ZOOM_LEVEL.toFloat())
                .build()
        return CameraUpdateFactory.newCameraPosition(cameraPosition)
    }

    /**
     * @param position where to draw the [com.google.android.gms.maps.model.Marker]
     * @return the [MarkerOptions] with given properties added to it.
     */

    fun getDriverMarkerOptions(position: LatLng): MarkerOptions {
        val options = getMarkerOptions(R.drawable.car_icon, position)
        options.flat(true)
        return options
    }

    private fun getMarkerOptions(resource: Int, position: LatLng): MarkerOptions {
        return MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(resource))
                .position(position)
    }
}