package com.example.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.model.Produto
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dao = ProdutosDao()
        Log.i("MainActivity", "onCreate: ${dao.buscaTodos()}")
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton7)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}



//        recyclerView.layoutManager = LinearLayoutManager(this)


//        Toast.makeText(this, "Pipoca", Toast.LENGTH_SHORT).show()
//        val view = TextView(this)
//        view.setText("cesta de frutas")

//        val nome = findViewById<TextView>(R.id.nome)
//        nome.text = "Pizza de calabresa"
//        val descricao = findViewById<TextView>(R.id.descricao)
//        descricao.text = "queijo, molho de tomate e calabresa"
//        val valor = findViewById<TextView>(R.id.valor)
//        valor.text = "39.90"

// Criando um Data Access Object - DAO

//data class XIX(
//    val oi: String,
//    val jorge: Int,
//    val ddd: Int,
//    val ssss: Int,
//    val aaa: Int,
//    val aeas: Int,
//    val asadff: Int
//)