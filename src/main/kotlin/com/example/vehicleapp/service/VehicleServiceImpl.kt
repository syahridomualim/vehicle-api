package com.example.vehicleapp.service

import com.example.vehicleapp.entity.Vehicle
import com.example.vehicleapp.model.CreateVehicleRequest
import com.example.vehicleapp.model.VehicleResponse
import com.example.vehicleapp.repository.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class VehicleServiceImpl @Autowired
constructor(private val vehicleRepository: VehicleRepository) : VehicleService {
    override fun saveVehicle(createVehicleRequest: CreateVehicleRequest) {
        val vehicle = Vehicle(
            tagNumber = createVehicleRequest.tagNumber,
            name = createVehicleRequest.name,
            color = createVehicleRequest.color
        )
        vehicleRepository.save(vehicle)
    }

    override fun getVehicle(tagNumber: String): VehicleResponse {
        val vehicle = vehicleRepository.findById(tagNumber)
        val vehicleResponse = vehicle.map {
            mapToVehicleResponse(it)
        }
        return vehicleResponse.orElseThrow()
    }

    override fun getVehicles(): List<VehicleResponse> {
        val vehicles = vehicleRepository.findAll().ifEmpty {
            Collections.emptyList()
        }

        return vehicles.map {
            mapToVehicleResponse(it)
        }
    }

    private fun mapToVehicleResponse(vehicle: Vehicle): VehicleResponse {
        return VehicleResponse(vehicle.tagNumber, vehicle.name, vehicle.color)
    }
}