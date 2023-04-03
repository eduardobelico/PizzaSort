package com.example.orgs

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        val produto = findViewById<TextView>(R.id.produto)
        produto.text = "Pizza de calabresa"
        val descricao = findViewById<TextView>(R.id.descricao)
        descricao.text = "queijo, molho de tomate e calabresa"
        val valor = findViewById<TextView>(R.id.valor)
        valor.text = "39.90"
//        Toast.makeText(this, "Pipoca", Toast.LENGTH_SHORT).show()
//        val view = TextView(this)
//        view.setText("cesta de frutas")
    }
}


//data class XIX(
//    val oi: String,
//    val jorge: Int,
//    val ddd: Int,
//    val ssss: Int,
//    val aaa: Int,
//    val aeas: Int,
//    val asadff: Int
//)