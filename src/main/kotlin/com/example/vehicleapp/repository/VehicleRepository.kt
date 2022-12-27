package com.example.vehicleapp.repository

import com.example.vehicleapp.entity.Vehicle
import org.springframework.data.jpa.repository.JpaRepository

interface VehicleRepository: JpaRepository<Vehicle, String> {
}