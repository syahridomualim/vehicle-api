package com.example.vehicleapp.controller

import com.example.vehicleapp.model.CreateVehicleRequest
import com.example.vehicleapp.model.EditVehicleRequest
import com.example.vehicleapp.model.HttpResponse
import com.example.vehicleapp.model.VehicleResponse
import com.example.vehicleapp.service.VehicleService
import com.example.vehicleapp.util.createResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/api/v1"])
class VehicleController @Autowired constructor(
    private val vehicleService: VehicleService
) {
    @PostMapping("/vehicles")
    fun saveVehicle(@Valid @RequestBody createVehicleRequest: CreateVehicleRequest): ResponseEntity<HttpResponse<Any>> {
        vehicleService.saveVehicle(createVehicleRequest)
        return createResponse(Collections.EMPTY_LIST, "data has been saved", HttpStatus.CREATED)
    }

    @GetMapping("/vehicles/{tag-number}")
    fun getVehicle(@PathVariable(value = "tag-number") tagNumber: String): ResponseEntity<HttpResponse<VehicleResponse?>> {
        val vehicleResponse =
            vehicleService.getVehicle(tagNumber) ?: throw NoSuchElementException("Vehicle by $tagNumber not found")
        return createResponse(
            vehicleResponse, "Successfully get vehicle by tag number ${vehicleResponse.tagNumber}", HttpStatus.OK
        )
    }

    @GetMapping("/vehicles")
    fun getVehicles(): ResponseEntity<HttpResponse<List<*>>> {
        val vehicles = vehicleService.getVehicles()
        return if (vehicles.isEmpty()) {
            createResponse(Collections.EMPTY_LIST, "Data is empty", HttpStatus.NOT_FOUND)
        } else {
            createResponse(vehicles, "Data has been successfully invoked", HttpStatus.OK)
        }
    }

    @PutMapping("/vehicles/{tag-number}")
    fun editVehicle(
        @PathVariable("tag-number") tagNumber: String, @Valid @RequestBody editVehicleRequest: EditVehicleRequest
    ): ResponseEntity<HttpResponse<Any>> {
        val vehicle = vehicleService.editVehicle(tagNumber, editVehicleRequest)
        return createResponse(vehicle, "successfully edit data", HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/vehicles/{tag-number}")
    fun deleteVehicle(@PathVariable("tag-number") tagNumber: String): ResponseEntity<HttpResponse<Any>> {
        vehicleService.deleteVehicle(tagNumber)
        return createResponse(Collections.EMPTY_LIST, "data has been deleted", HttpStatus.MOVED_PERMANENTLY)
    }

}