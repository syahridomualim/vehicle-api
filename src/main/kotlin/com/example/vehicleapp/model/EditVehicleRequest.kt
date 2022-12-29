package com.example.vehicleapp.model

import com.example.vehicleapp.entity.Color

data class EditVehicleRequest(
    val name: String,
    val color: Color
)