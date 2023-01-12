package com.example.vehicleapp.service

import com.example.vehicleapp.entity.Vehicle
import com.example.vehicleapp.exception.domain.VehicleAlreadyExist
import com.example.vehicleapp.model.CreateVehicleRequest
import com.example.vehicleapp.model.EditVehicleRequest
import com.example.vehicleapp.model.VehicleResponse
import com.example.vehicleapp.repository.VehicleRepository
import mu.KotlinLogging.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class VehicleServiceImpl @Autowired
constructor(private val vehicleRepository: VehicleRepository) : VehicleService {
    override fun saveVehicle(createVehicleRequest: CreateVehicleRequest) {
        validateVehicle(createVehicleRequest.tagNumber)
        val vehicle = Vehicle(
            tagNumber = createVehicleRequest.tagNumber,
            owner = createVehicleRequest.owner,
            brand = createVehicleRequest.brand,
            year = createVehicleRequest.year,
            cylinderCapacity = createVehicleRequest.cylinderCapacity,
            fuel = createVehicleRequest.fuel,
            color = createVehicleRequest.color
        )
        logger { }.info { "saved vehicle" }
        vehicleRepository.save(vehicle)
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun getVehicle(tagNumber: String): VehicleResponse? {
        val vehicle = vehicleRepository.findById(tagNumber).getOrNull()
        logger { }.info { "get vehicle with tag number $tagNumber" }
        return vehicle?.let { mapToVehicleResponse(it) }
    }

    override fun getVehicles(): List<VehicleResponse> {
        val vehicles = vehicleRepository.findAll().ifEmpty {
            logger { }.warn { "Vehicle is empty" }
            Collections.emptyList()
        }

        logger { }.info { "get all vehicles" }
        return vehicles.map {
            mapToVehicleResponse(it)
        }
    }

    override fun editVehicle(tagNumber: String, editVehicleRequest: EditVehicleRequest): VehicleResponse {
        val vehicle = vehicleRepository.findById(tagNumber).orElseThrow {
            NoSuchElementException("Vehicle doesn't exist")
        }

        vehicle.apply {
            owner = if (editVehicleRequest.owner == "") vehicle.owner else editVehicleRequest.owner
            brand = if (editVehicleRequest.brand == "") vehicle.brand else editVehicleRequest.brand
            year = if (editVehicleRequest.year == "") vehicle.year else editVehicleRequest.year
            cylinderCapacity = if (editVehicleRequest.cylinderCapacity == 0) vehicle.cylinderCapacity else editVehicleRequest.cylinderCapacity
            fuel = editVehicleRequest.fuel
            color = editVehicleRequest.color
        }

        vehicleRepository.save(vehicle)
        logger { }.info { "edited vehicle" }
        return mapToVehicleResponse(vehicle)
    }

    override fun deleteVehicle(tagNumber: String) {
        logger { }.info { "deleted vehicle" }
        vehicleRepository.deleteById(tagNumber)
    }

    private fun mapToVehicleResponse(vehicle: Vehicle): VehicleResponse {
        return VehicleResponse(vehicle.tagNumber, vehicle.owner, vehicle.color)
    }

    private fun validateVehicle(tagNumber: String): VehicleResponse? {
        val vehicle = getVehicle(tagNumber)
        if (vehicle != null) {
            throw VehicleAlreadyExist("Vehicle $tagNumber already exists")
        }
        return null
    }
}