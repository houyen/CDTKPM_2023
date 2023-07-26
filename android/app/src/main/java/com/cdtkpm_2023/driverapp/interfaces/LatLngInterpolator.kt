package com.cdtkpm_2023.driverapp.interfaces

import com.google.android.gms.maps.model.LatLng
import java.lang.Math.asin
import java.lang.Math.atan2
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Math.sqrt
import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import java.lang.Math.pow

interface LatLngInterpolator { 
    //The LatLngInterpolator interface helps to animate the driver car Marker.
    fun interpolate(fraction: Float, a: LatLng, b: LatLng): LatLng

    class Spherical: LatLngInterpolator {
        override fun interpolate(fraction: Float, a: LatLng, b: LatLng):LatLng{
            val fromLat: Double = toRadians(a.latitude)
            val fromLng: Double = toRadians(a.longitude)
            val toLat: Double = toRadians(b.latitude)
            val toLng: Double = toRadians(b.longitude)
            val cosFromLat: Double = cos(fromLat)
            val cosToLat: Double = cos(toLat)
            // Computes Spherical interpolation coefficients.
            val angle: Double = computeAngleBetween(fromLat, fromLng, toLat, toLng)
            val sinAngle: Double = sin(angle)
            if (sinAngle < 1E-6) {
                return a
            }
            val a: Double = sin((1 - fraction) * angle) / sinAngle
            val b: Double = sin(fraction * angle) / sinAngle
            // Converts from polar to vector and interpolate.
            val x: Double = a * cosFromLat * cos(fromLng) + b * cosToLat * cos(toLng)
            val y: Double = a * cosFromLat * sin(fromLng) + b * cosToLat * sin(toLng)
            val z: Double = a * sin(fromLat) + b * sin(toLat)
            // Converts interpolated vector back to polar.
            val lat: Double = atan2(z, sqrt(x * x + y * y))
            val lng: Double = atan2(y, x)
            return LatLng(toDegrees(lat), toDegrees(lng))
        }

        private fun computeAngleBetween(fromLat: Double, fromLng: Double, toLat: Double, toLng: Double): Double {
            // Haversine's formula
            val dLat: Double = fromLat - toLat
            val dLng: Double = fromLng - toLng
            return 2 * asin(sqrt(pow(sin(dLat / 2), 2.0) + cos(fromLat) * cos(toLat) * pow(sin(dLng / 2), 2.0)))
        }
    }
}