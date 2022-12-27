package com.example.vehicleapp.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun springDoc(): OpenAPI {
        return OpenAPI().info(
            Info().apply {
                title = "vehicle-app"
                version = "1.0.0"
            }
        )
    }
}