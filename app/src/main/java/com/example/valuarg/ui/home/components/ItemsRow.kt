package ui.home.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.valuarg.ui.common.ItemCard
import com.example.valuarg.data.model.Budget
import com.example.valuarg.data.model.Project
import com.example.valuarg.ui.home.HomeTab

/**
 * Carrusel de items (presupuestos o proyectos) en un LazyRow.
 * Ocupa un 25% de la pantalla aprox. (definido externamente).
 */
@Composable
fun ItemsRow(
    selectedTab: HomeTab,
    budgets: List<Budget>,
    projects: List<Project>,
    onBudgetClick: (String) -> Unit,
    onProjectClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (selectedTab == HomeTab.PRESUPUESTOS) {
            items(budgets) { budget ->
                ItemCard(
                    title = budget.title,
                    subtitle = budget.dateCreated.toString(),
                    amount = budget.total.toCurrencyString(),
                    onClick = { onBudgetClick(budget.id) }
                )
            }
        } else {
            items(projects) { project ->
                ItemCard(
                    title = "Proyecto: ${project.id.take(6)}", // mostrar ID parcial
                    subtitle = project.dateConfirmed.toString(),
                    amount = "S/ ", // Placeholder; se obtendría monto desde Budget
                    onClick = { onProjectClick(project.id) }
                )
            }
        }
    }
}