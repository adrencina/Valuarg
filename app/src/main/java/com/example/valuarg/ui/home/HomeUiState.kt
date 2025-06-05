package com.example.valuarg.ui.home

import com.example.valuarg.data.model.Budget
import com.example.valuarg.data.model.Project

/**
 * Estado completo de la Home.
 */
data class HomeUiState(
    val selectedTab: HomeTab = HomeTab.PRESUPUESTOS,
    val budgets: List<Budget> = emptyList(),
    val projects: List<Project> = emptyList(),
    val selectedBudgetId: String? = null,
    val selectedProjectId: String? = null
)