package ui.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valuarg.ui.common.EmptyStateMessage
import com.example.valuarg.ui.common.SectionHeader
import com.example.valuarg.data.model.Budget
import com.example.valuarg.data.model.Project
import com.example.valuarg.ui.home.HomeTab

/**
 * Lista de detalles dinámica en LazyColumn:
 * - Si PRESUPUESTOS: muestra detalles del presupuesto seleccionado
 * - Si PROYECTOS: muestra detalles del proyecto seleccionado
 */
@Composable
fun ItemsList(
    selectedTab: HomeTab,
    budgets: List<Budget>,
    projects: List<Project>,
    selectedBudgetId: String?,
    selectedProjectId: String?,
    modifier: Modifier = Modifier,
    onConfirmBudget: (String) -> Unit,
    onTaskToggle: (String, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (selectedTab == HomeTab.PRESUPUESTOS) {
            val budget = budgets.firstOrNull { it.id == selectedBudgetId }
            if (budget != null) {
                item {
                    // Sección Materiales
                    SectionHeader("Materiales")
                    budget.materials.forEach { mat ->
                        BudgetDetailItem(
                            name = mat.name,
                            quantityOrHours = mat.quantity.toString(),
                            unitPrice = mat.unitPrice.toCurrencyString(),
                            totalPrice = mat.totalItem.toCurrencyString()
                        )
                    }
                }
                item {
                    // Sección Servicios
                    SectionHeader("Servicios")
                    budget.services.forEach { serv ->
                        BudgetDetailItem(
                            name = serv.description,
                            quantityOrHours = serv.laborHours.toString(),
                            unitPrice = serv.ratePerHour.toCurrencyString(),
                            totalPrice = serv.totalItem.toCurrencyString()
                        )
                    }
                }
                item {
                    // Resumen final
                    SummaryCard(
                        subtotal = budget.subTotal.toCurrencyString(),
                        iva = budget.ivaAmount.toCurrencyString(),
                        total = budget.total.toCurrencyString()
                    )
                }
                item {
                    // Botón para confirmar presupuesto si está en BORRADOR
                    if (budget.status == Budget.BudgetStatus.DRAFT) {
                        ConfirmBudgetButton(onConfirm = { onConfirmBudget(budget.id) })
                    }
                }
            } else {
                item {
                    EmptyStateMessage("No hay presupuesto seleccionado. Toca una tarjeta arriba.")
                }
            }
        } else {
            val project = projects.firstOrNull { it.id == selectedProjectId }
            if (project != null) {
                item {
                    // Sección Estado del Proyecto
                    SectionHeader("Estado del proyecto")
                    ProjectProgressSection(status = project.status)
                }
                item {
                    // Sección Tareas
                    SectionHeader("Tareas")
                    project.tasks.forEach { task ->
                        TaskItem(
                            taskName = task.name,
                            isChecked = task.isDone,
                            onCheckedChange = { isChecked -> onTaskToggle(task.id, isChecked) }
                        )
                    }
                }
                item {
                    // Sección Galería
                    SectionHeader("Galería")
                    ProjectGalleryRow(imageUris = project.photos)
                }
                item {
                    // Sección Materiales usados (del presupuesto original)
                    SectionHeader("Materiales usados")
                    // Placeholder: en implementación real, se obtendrían materiales desde el presupuesto
                }
            } else {
                item {
                    EmptyStateMessage("No hay proyecto seleccionado. Toca una tarjeta arriba.")
                }
            }
        }
    }
}