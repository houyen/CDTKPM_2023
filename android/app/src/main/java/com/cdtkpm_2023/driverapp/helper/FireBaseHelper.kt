package com.cdtkpm_2023.driverapp.helper

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.spartons.driverapp.model.Driver


// The above MarkerAnimationHelper class animate the driver car 
// Marker from the previous location to user new Location.
class FirebaseHelper constructor(driverId: String) {

    companion object {
        private const val ONLINE_DRIVERS = "online_drivers"
    }

    private val onlineDriverDatabaseReference: DatabaseReference = FirebaseDatabase
            .getInstance()
            .reference
            .child(ONLINE_DRIVERS)
            .child(driverId)
    /* 
    onlineDriverDatabaseReference: When creating a DatabaseReference, 
    we’re adding two children one for online drivers node and another one 
    for the Driver itself. We need to inform firebase real-time database 
    in which node to update the Diver that’s why I’m setting driverId as 
    a top node and below the is the complete Driver object. 
    The driverId must be unique because it differs in whole online drivers node which specific driver node to update.
     */
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
    //updateDriver: Update the Driver new Location to firebase real-time database.
    fun deleteDriver() {
        onlineDriverDatabaseReference
                .removeValue()
    }
    //deleteDriver: Remove the Driver from firebase real-time database.
}