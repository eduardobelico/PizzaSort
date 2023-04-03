package com.example.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Produto
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
//        val nome = findViewById<TextView>(R.id.nome)
//        nome.text = "Pizza de calabresa"
//        val descricao = findViewById<TextView>(R.id.descricao)
//        descricao.text = "queijo, molho de tomate e calabresa"
//        val valor = findViewById<TextView>(R.id.valor)
//        valor.text = "39.90"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(
            context = this, produtos = listOf(
                Produto(
                    nome = "Pizza de Calabresa",
                    descricao = "queijo, molho de tomate e calabresa",
                    valor = BigDecimal("38.90")
                ),
                Produto(
                    nome = "Pizza Portuguesa",
                    descricao = "queijo, molho de tomate, cebola, presunto, ovos e azeitona",
                    valor = BigDecimal("38.90")
                ),
                Produto(
                    nome = "Pizza Marguerita",
                    descricao = "queijo, molho de tomate, tomate e manjericão",
                    valor = BigDecimal("38.90")
                ),
                Produto(
                    nome = "Pizza Mussarela",
                    descricao = "queijo mussarela e molho de tomate",
                    valor = BigDecimal("35.90")
                )
            )
        )

        recyclerView.layoutManager = LinearLayoutManager(this)


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