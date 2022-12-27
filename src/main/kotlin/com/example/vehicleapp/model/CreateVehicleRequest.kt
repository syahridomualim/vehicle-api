package com.example.vehicleapp.model

import com.example.vehicleapp.entity.Color
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CreateVehicleRequest(
    @field:NotEmpty(message = "Please provide your tag number")
    val tagNumber: String,
    @field:Size(min = 5, message = "Please provide in the value minimum of 5 characters")
    val name: String,
    val color: Color
)