package com.example.vehicleapp.validation

import com.example.vehicleapp.entity.Color
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ColorValidator::class])
annotation class ColorValidation ( //error message
    val message: String = "",  //represents group of constraints
    val anyOf: Array<Color> = [],
    val groups: Array<KClass<*>> = [],  //represents additional information about annotation
    val payload: Array<KClass<out Payload>> = []
)
