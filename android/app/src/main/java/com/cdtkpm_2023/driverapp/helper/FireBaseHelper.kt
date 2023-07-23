package com.cdtkpm_2023.driverapp.helper

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.spartons.driverapp.model.Driver


// The above MarkerAnimationHelper class animate the driver car Marker from the previous location to user new Location.
class FirebaseHelper constructor(driverId: String) {

    companion object {
        private const val ONLINE_DRIVERS = "online_drivers"
    }

    private val onlineDriverDatabaseReference: DatabaseReference = FirebaseDatabase
            .getInstance()
            .reference
            .child(ONLINE_DRIVERS)
            .child(driverId)

    init {
        onlineDriverDatabaseReference
                .onDisconnect()
                .removeValue()
    }

    fun updateDriver(driver: Driver) {
        onlineDriverDatabaseReference
                .setValue(driver)
        Log.e("Driver Info", " Updated")
    }

    fun deleteDriver() {
        onlineDriverDatabaseReference
                .removeValue()
    }
}