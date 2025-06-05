package com.example.valuarg.data.model

import java.time.LocalDate
import java.util.UUID

/**
 * Entidad Budget, que incluye listas de MaterialItem y ServiceItem.
 */
data class Budget(
    val id: String = UUID.randomUUID().toString(),
    val clientId: String,
    val title: String,
    val dateCreated: LocalDate = LocalDate.now(),
    val materials: List<MaterialItem> = emptyList(),
    val services: List<ServiceItem> = emptyList(),
    val ivaPercent: Double = 21.0,
    val discountPercent: Double = 0.0,
    val status: BudgetStatus = BudgetStatus.DRAFT,
    val syncStatus: SyncStatus = SyncStatus.PENDING
) {
    enum class BudgetStatus { DRAFT, CONFIRMED }

    // Calcula subtotal de materiales
    val subTotalMaterials: Double
        get() = materials.sumOf { it.totalItem }

    // Calcula subtotal de servicios
    val subTotalServices: Double
        get() = services.sumOf { it.totalItem }

    // Calcula subtotal general
    val subTotal: Double
        get() = subTotalMaterials + subTotalServices

    // Calcula monto de IVA
    val ivaAmount: Double
        get() = subTotal * ivaPercent / 100

    // Calcula total (aplica IVA y descuento)
    val total: Double
        get() = (subTotal + ivaAmount) * (1 - discountPercent / 100)
}