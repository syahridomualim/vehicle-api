package com.example.vehicleapp.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id

@Entity
data class Vehicle(
    @Id
    var tagNumber: String,
    var name: String,
    @Enumerated(value = EnumType.STRING)
    var color: Color
)
