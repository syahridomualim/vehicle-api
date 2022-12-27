package com.example.vehicleapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VehicleAppApplication

fun main(args: Array<String>) {
    runApplication<VehicleAppApplication>(*args)
}
