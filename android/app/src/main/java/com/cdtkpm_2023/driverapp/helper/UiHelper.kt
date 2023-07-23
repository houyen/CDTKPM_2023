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

class UiHelper {
    // isPlayServicesAvailable: This method checks that if the user currently has Google Play Services installed or not.
    fun isPlayServicesAvailable(context: Context): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(context)
        return ConnectionResult.SUCCESS == status
    }

    // isHaveLocationPermission: Checks that if a user gives the Location permission or not.
    fun isHaveLocationPermission(context: Context): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // isLocationProviderEnabled: Checks if the Location provider enabled or not. If not then open the Settings activity and turn on the Location Provider.
    fun isLocationProviderEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    // showPositiveDialogWithListener: Utility function to show the Dialog when the user has not enabled the location provider in mobile settings.
    fun showPositiveDialogWithListener(callingClassContext: Context, title: String, content: String, positiveNegativeListener: IPositiveNegativeListener, positiveText: String, cancelable: Boolean) {
        buildDialog(callingClassContext, title, content)
                .builder
                .positiveText(positiveText)
                .positiveColor(getColor(R.color.colorPrimary, callingClassContext))
                .onPositive { _, _ -> positiveNegativeListener.onPositive() }
                .cancelable(cancelable)
                .show()
    }

    private fun buildDialog(callingClassContext: Context, title: String, content: String): MaterialDialog {
        return MaterialDialog.Builder(callingClassContext)
                .title(title)
                .content(content)
                .build()
    }


    private fun getColor(color: Int, context: Context): Int {
        return ContextCompat.getColor(context, color)
    }

    fun getLocationRequest() : LocationRequest {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 3000
        return locationRequest
    }
}