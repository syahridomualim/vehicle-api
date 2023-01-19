package com.example.vehicleapp.service

import com.example.vehicleapp.entity.Color
import com.example.vehicleapp.entity.Fuel
import com.example.vehicleapp.entity.Vehicle
import com.example.vehicleapp.repository.VehicleRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
class VehicleServiceTest {

    private val vehicleRepository: VehicleRepository = Mockito.mock(VehicleRepository::class.java)

    private var vehicleService: VehicleService = VehicleServiceImpl(vehicleRepository)

    private val vehicles = listOf(
        Vehicle(
            "B 8795 KBU",
            "Yana",
            "HONDA",
            "2014",
            125,
            Fuel.PETROL,
            Color.BLUE
        ),
        Vehicle(
            "B 4546 BRU",
            "Mualim",
            "YAMAHA",
            "2014",
            150,
            Fuel.PETROL,
            Color.RED
        ),
        Vehicle(
            "B 7453 FHS",
            "Samsuri",
            "SUZUKI",
            "2014",
            150,
            Fuel.PETROL,
            Color.GREEN
        )
    )

    @Before
    fun setUp() {
        val yana = vehicles[0]
        val mualim = vehicles[1]
        val samsuri = vehicles[2]

        vehicleRepository.saveAll(vehicles)

        Mockito.`when`(vehicleRepository.findById(yana.tagNumber)).thenReturn(Optional.of(yana))
        Mockito.`when`(vehicleRepository.findById(mualim.tagNumber)).thenReturn(Optional.of(mualim))
        Mockito.`when`(vehicleRepository.findById(samsuri.tagNumber)).thenReturn(Optional.of(samsuri))
        Mockito.`when`(vehicleRepository.findById("wrong_id")).thenReturn(Optional.empty())
        Mockito.`when`(vehicleRepository.findAll()).thenReturn(vehicles)
    }

    @After
    fun tearDown() {
        vehicleRepository.deleteAll()
    }

    @Test
    fun testVehicleValid() {
        val id = "B 7453 FHS"
        val vehicle = vehicleService.getVehicle(id)
        Assertions.assertEquals(id, vehicle?.tagNumber)
        verifyFindByIdIsCalledOnce(id)
    }

    @Test
    fun testVehicleInvalid() {
        val id = "wrong_id"
        val existUser = vehicleService.getVehicle(id)
        Assertions.assertTrue(existUser?.tagNumber.equals(null))

        verifyFindByIdIsCalledOnce(id)
    }

    @Test
    fun testAllVehicles() {
        val list = vehicleService.getVehicles()
        Assertions.assertEquals(list.size, vehicles.size)
        Assertions.assertTrue(list.stream().anyMatch {
            it.tagNumber == "B 7453 FHS"
        })
    }

    private fun verifyFindByIdIsCalledOnce(id: String) {
        Mockito.verify(vehicleRepository, VerificationModeFactory.times(1)).findById(id);
        Mockito.reset(vehicleRepository);
    }

    private fun verifyFindAllVehiclesCalledOnce() {
        Mockito.verify(vehicleRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(vehicleRepository);
    }
}
