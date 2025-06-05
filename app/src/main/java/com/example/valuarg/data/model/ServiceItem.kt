package com.example.valuarg.data.model

import java.util.UUID

/**
 * Representa un servicio (mano de obra) en un presupuesto.
 */
data class ServiceItem(
    val id: String = UUID.randomUUID().toString(),
    val budgetId: String,  // ID del presupuesto al que pertenece
    val description: String,
    val laborHours: Double,
    val ratePerHour: Double,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    // Calcula el total de este servicio
    val totalItem: Double
        get() = laborHours * ratePerHour
}