package com.example.valuarg.ui.home.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.valuarg.ui.common.SectionDivider
import com.example.valuarg.ui.common.BottomNavBar
import com.example.valuarg.ui.common.BottomNavItem
import com.example.valuarg.ui.home.viewmodel.HomeViewModel
import ui.home.components.TopToggleBar
import ui.home.components.ItemsRow
import ui.home.components.ItemsList

/**
 * Pantalla principal Home, que contiene:
 * - TopToggleBar (Proyectos / Presupuestos)
 * - ItemsRow (carrusel de tarjetas)
 * - SectionDivider (separador)
 * - ItemsList (detalles en LazyColumn)
 * - BottomNavBar (barra inferior)
 */
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopToggleBar(
                selectedTab = uiState.selectedTab,
                onTabSelected = { viewModel.onTabSelected(it) }
            )
        },
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem("home", Icons.Default.Home, "Home"),
                    BottomNavItem("budgets", Icons.Default.Description, "Presupuestos"),
                    BottomNavItem("projects", Icons.AutoMirrored.Filled.List, "Proyectos"),
                    BottomNavItem("camera", Icons.Default.CameraAlt, "Fotos"),
                    BottomNavItem("settings", Icons.Default.Settings, "Ajustes")
                ),
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // LazyRow (25% pantalla)
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            ItemsRow(
                selectedTab = uiState.selectedTab,
                budgets = uiState.budgets,
                projects = uiState.projects,
                onBudgetClick = { viewModel.onBudgetClicked(it) },
                onProjectClick = { viewModel.onProjectClicked(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.25f)
            )

            // Divider
            SectionDivider()

            // LazyColumn (75% restante)
            ItemsList(
                selectedTab = uiState.selectedTab,
                budgets = uiState.budgets,
                projects = uiState.projects,
                selectedBudgetId = uiState.selectedBudgetId,
                selectedProjectId = uiState.selectedProjectId,
                onConfirmBudget = { viewModel.onConfirmBudget(it) },
                onTaskToggle = { id, done -> viewModel.onTaskToggle(id, done) }
            )
        }
    }
}