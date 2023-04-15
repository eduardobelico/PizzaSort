package com.example.orgs.extensions

import android.view.View
import android.widget.ImageView
import coil.load
import com.example.orgs.R
import com.example.orgs.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


fun BigDecimal.formatToBrazilianCurrency(): String {

    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}

//fun Produto.tentaCarregarProduto() {
//    intent.
//}

fun ImageView.tentaCarregarImagem(url: String? = null) {
    if (url != null) {
        visible()

        load(url) {
            fallback(R.drawable.erro)
            error(R.drawable.erro)
            placeholder(R.drawable.placeholder)
            crossfade(1000)
        }
    } else visibilityGone()

}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visibilityGone() {
    visibility = View.GONE
}



