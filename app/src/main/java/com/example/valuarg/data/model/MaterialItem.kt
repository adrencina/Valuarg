package com.example.valuarg.data.model

import java.util.UUID

/**
 * Representa un material en un presupuesto, con cantidad y precio unitario.
 */
data class MaterialItem(
    val id: String = UUID.randomUUID().toString(),
    val budgetId: String,  // ID del presupuesto al que pertenece
    val name: String,
    val quantity: Int,
    val unitPrice: Double,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    // Calcula el total de este ítem
    val totalItem: Double
        get() = quantity * unitPrice
}