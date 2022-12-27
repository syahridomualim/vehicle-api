package com.example.vehicleapp.model

import com.example.vehicleapp.entity.Color

data class VehicleResponse(
    val tagNumber: String,
    val name: String,
    val color: Color
)