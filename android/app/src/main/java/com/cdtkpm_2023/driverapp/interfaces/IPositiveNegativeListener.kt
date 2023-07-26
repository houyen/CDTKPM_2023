package com.cdtkpm_2023.driverapp.interfaces

@FunctionalInterface
interface IPositiveNegativeListener {
    fun onPositive()
    fun onNegative(){}
}