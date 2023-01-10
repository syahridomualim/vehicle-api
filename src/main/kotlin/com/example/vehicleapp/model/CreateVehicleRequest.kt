    package com.example.vehicleapp.model

    import com.example.vehicleapp.entity.Color
import com.example.vehicleapp.entity.Fuel
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateVehicleRequest(
    @field:Pattern(regexp = "^[A-Z]{2} [0-9]{4} [A-Z]{1,3}$", message = "Please provide your tag number")
    val tagNumber: String,
    @field:Size(min = 5, message = "Please provide the name minimum of 5 characters")
    val owner: String,
    @field:NotEmpty(message = "Please provide the brand")
    val brand: String,
    @field:NotEmpty(message = "Please provide the year production")
    @field:Pattern(regexp = "^\\d{4}$")
    val year: String,
    @field:Size(min = 1, message = "Please provide cylinder capacity")
    val cylinderCapacity: String,
    val fuel: Fuel,
    val color: Color
)