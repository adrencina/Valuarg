package com.example.valuarg.data.model

import java.util.UUID

/**
 * Representa a un cliente asociado a presupuestos y proyectos.
 */
data class Client(
    val id: String = UUID.randomUUID().toString(), // ID único generado
    val name: String,
    val phone: String,
    val address: String? = null,
    val syncStatus: SyncStatus = SyncStatus.PENDING
)

enum class SyncStatus {
    PENDING, // Requiere sincronización a Firebase
    SYNCED   // Ya sincronizado
}