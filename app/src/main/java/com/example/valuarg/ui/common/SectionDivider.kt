package com.example.valuarg.ui.common

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Divider estilizado con Material3.
 */
@Composable
fun SectionDivider(modifier: Modifier = Modifier) {
    Divider(
        color = MaterialTheme.colorScheme.outline,
        thickness = 1.dp,
        modifier = modifier
    )
}