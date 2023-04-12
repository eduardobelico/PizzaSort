package com.example.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


fun BigDecimal.formatToBrazilianCurrency(): String{

    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)

}



