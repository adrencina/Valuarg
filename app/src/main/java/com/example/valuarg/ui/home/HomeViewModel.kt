package com.example.valuarg.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla Home.
 * Controla el estado y la lógica de convertir presupuestos a proyectos.
 */
class HomeViewModel : ViewModel() {

    // Estado interno mutable
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        // Cargar datos iniciales desde repositorios (Room)
        viewModelScope.launch {
            // Ejemplo: obtener la lista de budgets y projects del repositorio local
            // _uiState.value = uiState.value.copy(budgets = repo.getBudgets(), projects = repo.getProjects())
        }
    }

    // Maneja la selección de tab
    fun onTabSelected(tab: HomeTab) {
        _uiState.value = uiState.value.copy(selectedTab = tab)
    }

    // Maneja click en un presupuesto
    fun onBudgetClicked(budgetId: String) {
        _uiState.value = uiState.value.copy(selectedBudgetId = budgetId, selectedProjectId = null)
    }

    // Maneja click en un proyecto
    fun onProjectClicked(projectId: String) {
        _uiState.value = uiState.value.copy(selectedProjectId = projectId, selectedBudgetId = null)
    }

    // Convierte un presupuesto en proyecto
    fun onConfirmBudget(budgetId: String) {
        viewModelScope.launch {
            // 1. Actualizar Budget en Room: status = CONFIRMED, syncStatus = PENDING
            // 2. Crear nuevo Project en Room: con budgetId, status = IN_PROGRESS, syncStatus = PENDING
            // 3. Actualizar estado local para reflejar cambios
            // _uiState.value = ...

            // Placeholder: eliminar selección del presupuesto
            _uiState.value = uiState.value.copy(selectedBudgetId = null)
        }
    }

    // Maneja toggleo de tarea en proyecto
    fun onTaskToggle(taskId: String, isDone: Boolean) {
        viewModelScope.launch {
            // Actualizar TaskItem en Room y luego sincronizar
        }
    }
}