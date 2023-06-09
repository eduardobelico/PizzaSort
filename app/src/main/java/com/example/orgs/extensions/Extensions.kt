package com.example.orgs.extensions

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import coil.load
import com.example.orgs.R
import java.math.BigDecimal
import java.security.MessageDigest
import java.text.NumberFormat
import java.util.*


fun BigDecimal.formatToBrazilianCurrency(): String {

    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}

fun Context.vaiPara(clazz: Class<*>, intent: Intent.() -> Unit = {}) {
    Intent(this, clazz)
        .apply {
            intent()
            startActivity(this)
        }
}

fun Context.toast(mensagem: String) {
    Toast.makeText(this, mensagem, Toast.LENGTH_SHORT)
        .show()
}

fun String.toHash(
    algoritmo: String = "SHA-256"
): String {
    return MessageDigest
        .getInstance(algoritmo)
        .digest(this.toByteArray())
        .fold("", { str, byte ->
            str + "%02x".format(byte)
        })
}

fun ImageView.tentaCarregarImagem(url: String? = null, fallback: Int = R.drawable.imagem_padrao) {
//    if (url != null) {
//        visible()

    load(url) {
        fallback(fallback)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
        crossfade(1000)
    }
//    } else visibilityGone()

}
// Outra possibilidade usando VISIBILITY!

//fun View.invisible() {
//    visibility = View.INVISIBLE
//}
//
//fun View.visible() {
//    visibility = View.VISIBLE
//}
//
//fun View.visibilityGone() {
//    visibility = View.GONE
//}



