package com.example.vehicleapp.service

import com.example.vehicleapp.entity.Vehicle
import com.example.vehicleapp.exception.domain.VehicleAlreadyExist
import com.example.vehicleapp.logger.Logger.log
import com.example.vehicleapp.model.CreateVehicleRequest
import com.example.vehicleapp.model.EditVehicleRequest
import com.example.vehicleapp.model.VehicleResponse
import com.example.vehicleapp.repository.VehicleRepository
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
            name = createVehicleRequest.name,
            color = createVehicleRequest.color
        )
        log.info("saved new vehicle")
        vehicleRepository.save(vehicle)
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun getVehicle(tagNumber: String): VehicleResponse? {
        val vehicle = vehicleRepository.findById(tagNumber).getOrNull()
        return vehicle?.let { mapToVehicleResponse(it) }
    }

    override fun getVehicles(): List<VehicleResponse> {
        val vehicles = vehicleRepository.findAll().ifEmpty {
            log.warn("Vehicle is empty")
            Collections.emptyList()
        }

        log.info("get all vehicles")
        return vehicles.map {
            mapToVehicleResponse(it)
        }
    }

    override fun editVehicle(tagNumber: String, editVehicleRequest: EditVehicleRequest): VehicleResponse {
        val vehicle = vehicleRepository.findById(tagNumber).orElseThrow {
            NoSuchElementException("Vehicle doesn't exist")
        }

        vehicle.apply {
            name = editVehicleRequest.name
            color = editVehicleRequest.color
        }

        log.info("edited vehicle")
        return mapToVehicleResponse(vehicle)
    }

    override fun deleteVehicle(tagNumber: String) {
        log.info("deleted vehicle")
        vehicleRepository.deleteById(tagNumber)
    }

    private fun mapToVehicleResponse(vehicle: Vehicle): VehicleResponse {
        return VehicleResponse(vehicle.tagNumber, vehicle.name, vehicle.color)
    }

    private fun validateVehicle(tagNumber: String): VehicleResponse? {
        val vehicle = getVehicle(tagNumber)
        if (vehicle != null) {
            throw VehicleAlreadyExist("Vehicle $tagNumber already exists")
        }
        return null
    }
}