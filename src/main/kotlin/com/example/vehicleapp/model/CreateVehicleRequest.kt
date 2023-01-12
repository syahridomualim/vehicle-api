    package com.example.vehicleapp.model

    import com.example.vehicleapp.entity.Color
import com.example.vehicleapp.entity.Fuel
    import jakarta.validation.constraints.*

    data class CreateVehicleRequest(
    @field:Pattern(regexp = "^[A-Z]{1,3} [0-9]{4} [A-Z]{1,3}$", message = "Please provide your tag number. Ex: N 4354 KLM")
    val tagNumber: String,
    @field:Size(min = 5, message = "Please provide the name minimum of 5 characters")
    val owner: String,
    @field:NotEmpty(message = "Please provide the brand")
    val brand: String,
    @field:NotEmpty(message = "Please provide the year production")
    @field:Pattern(regexp = "^\\d{4}$")
    val year: String,
    @field:Min(value=1, message="Must be equal or greater than 1")
    @field:Max(value=1000, message="Must be equal or less than 1000")
    val cylinderCapacity: Int,
    val fuel: Fuel,
    val color: Color
)