package utils

import java.text.NumberFormat
import java.util.Locale

/**
 * Función de extensión para formatear Double como moneda local argentina.
 */
fun Double.toCurrencyString(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
    return formatter.format(this)
}