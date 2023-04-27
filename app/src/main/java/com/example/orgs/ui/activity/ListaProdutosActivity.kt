package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.model.Produto
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.*

class ListaProdutosActivity : AppCompatActivity() {

    private val adapter = ListaProdutosAdapter(context = this@ListaProdutosActivity)
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()
        val scope = MainScope()
        scope.launch {
            val produtos = withContext(Dispatchers.IO) {
                produtoDao.buscaTodos()
            }
            adapter.setData(produtos)
        }
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        vaiParaDetalhesProduto()
        adapter.clicarEmEditar
        adapter.clicarEmRemover
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun vaiParaDetalhesProduto() {
        adapter.clicarNoProduto = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ordenar_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val produtosOrdenados: List<Produto>? = when (item.itemId) {
            R.id.menu_ordenar_produtos_nome_asc -> produtoDao.ordenarPorNomeAsc()
            R.id.menu_ordenar_produtos_nome_desc -> produtoDao.ordenarPorNomeDesc()
            R.id.menu_ordenar_produtos_descricao_asc -> produtoDao.ordenarPorDescricaoAsc()
            R.id.menu_ordenar_produtos_descricao_desc -> produtoDao.ordenarPorDescricaoDesc()
            R.id.menu_ordenar_produtos_valor_asc -> produtoDao.ordenarPorValorAsc()
            R.id.menu_ordenar_produtos_valor_desc -> produtoDao.ordenarPorValorDesc()
            R.id.menu_ordenar_produtos_sem_ordem -> produtoDao.buscaTodos()
            else -> null
        }
        produtosOrdenados?.let {
            adapter.setData(it)
        }
        return super.onOptionsItemSelected(item)
    }

}


