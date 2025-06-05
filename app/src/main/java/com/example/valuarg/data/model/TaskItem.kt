package com.example.valuarg.data.model

import java.util.UUID

/**
 * Representa una tarea dentro de un proyecto.
 */
data class TaskItem(
    val id: String = UUID.randomUUID().toString(),
    val projectId: String, // ID del proyecto
    val name: String,
    val isDone: Boolean = false,
    val syncStatus: SyncStatus = SyncStatus.PENDING
)