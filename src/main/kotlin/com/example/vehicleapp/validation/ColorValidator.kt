package com.example.vehicleapp.validation

import com.example.vehicleapp.entity.Color
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ColorValidator : ConstraintValidator<ColorValidation, Color> {

    private lateinit var subset: Array<Color>

    override fun initialize(constraintAnnotation: ColorValidation?) {
        subset = constraintAnnotation?.anyOf!!
    }

    override fun isValid(color: Color?, context: ConstraintValidatorContext?): Boolean {
        return color != null && subset.contains(color)
    }

}
