package com.cdtkpm_2023.driverapp.model

data class Driver(val lat: Double, val lng: Double, val bearing: Float, val driverId: String = "0000", val isOnline: Boolean)