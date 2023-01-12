package com.example.vehicleapp.entity

import jakarta.persistence.*

@Table(name = "vehicle")
@Entity
data class Vehicle(
    @Id
    var tagNumber: String,
    var owner: String,
    var brand: String,
    var year: String,
    var cylinderCapacity: Int,
    @Enumerated(value = EnumType.STRING)
    var fuel: Fuel,
    @Enumerated(value = EnumType.STRING)
    var color: Color
)
