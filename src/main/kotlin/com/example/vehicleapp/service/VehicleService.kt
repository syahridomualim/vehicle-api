package com.example.vehicleapp.service

import com.example.vehicleapp.model.CreateVehicleRequest
import com.example.vehicleapp.model.EditVehicleRequest
import com.example.vehicleapp.model.VehicleResponse

interface VehicleService {
    fun saveVehicle(createVehicleRequest: CreateVehicleRequest)
    fun getVehicle(tagNumber: String): VehicleResponse?
    fun getVehicles(): List<VehicleResponse>
    fun editVehicle(tagNumber: String, editVehicleRequest: EditVehicleRequest): VehicleResponse
    fun deleteVehicle(tagNumber: String)
}