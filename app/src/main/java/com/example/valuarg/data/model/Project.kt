package com.example.valuarg.data.model

import java.time.LocalDate
import java.util.UUID

/**
 * Entidad Project, creada a partir de un presupuesto confirmado.
 */
data class Project(
    val id: String = UUID.randomUUID().toString(),
    val budgetId: String,
    val clientId: String,
    val dateConfirmed: LocalDate = LocalDate.now(),
    val status: ProjectStatus = ProjectStatus.IN_PROGRESS,
    val tasks: List<TaskItem> = emptyList(),
    val photos: List<String> = emptyList(), // URIs de fotos
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    enum class ProjectStatus { IN_PROGRESS, COMPLETED, PAUSED }
}