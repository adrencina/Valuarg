package ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Botón para confirmar un presupuesto, visible solo en estado DRAFT.
 */
@Composable
fun ConfirmBudgetButton(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onConfirm,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Confirmar presupuesto", style = MaterialTheme.typography.labelLarge)
    }
}