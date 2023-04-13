package com.example.orgs.extensions

import android.widget.ImageView
import coil.load
import com.example.orgs.R
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


fun BigDecimal.formatToBrazilianCurrency(): String {

    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}



