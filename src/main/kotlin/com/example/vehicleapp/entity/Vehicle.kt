package com.example.vehicleapp.entity

import jakarta.annotation.Nullable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table

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
