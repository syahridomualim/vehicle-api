package com.example.vehicleapp.model

import com.example.vehicleapp.entity.Color
import com.example.vehicleapp.entity.Fuel

data class EditVehicleRequest(
    var tagNumber: String,
    var owner: String,
    var brand: String,
    var year: String,
    var cylinderCapacity: Int,
    var fuel: Fuel,
    var color: Color
)