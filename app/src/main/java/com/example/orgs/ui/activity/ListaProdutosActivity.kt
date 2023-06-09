package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.extensions.vaiPara
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class ListaProdutosActivity : UsuarioBaseActivity() {

    private val adapter = ListaProdutosAdapter(context = this)
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
        lifecycleScope.launch {
            launch {
                usuario
                    .filterNotNull()
                    .collect() { usuario ->
                        buscaProdutosUsuario(usuario.id)
                    }
            }
        }
    }

    private suspend fun buscaProdutosUsuario(usuarioId: String) {
        produtoDao.buscaTodosDoUsuario(usuarioId).collect() { produtos ->
            adapter.setData(produtos)
        }
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        vaiParaDetalhesProduto()
    }

    private fun vaiParaDetalhesProduto() {
        adapter.clicarNoProduto = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMenuLista(item)
        return super.onOptionsItemSelected(item)
    }

    private fun setMenuLista(item: MenuItem) {
        when (item.itemId) {

            R.id.menu_logout -> {
                lifecycleScope.launch {
                    deslogaUsuario()
                }
            }

            R.id.menu_ordenar_todos_produtos -> {
                vaiPara(TodosProdutosActivity::class.java)
            }

            R.id.menu_ordenar_produtos_nome_asc -> {
                lifecycleScope.launch {
                    produtoDao.ordenarPorNomeAsc().collect() {
                        adapter.setData(it)
                    }
                }
            }
            R.id.menu_ordenar_produtos_nome_desc -> {
                lifecycleScope.launch {
                    produtoDao.ordenarPorNomeDesc().collect() {
                        adapter.setData(it)
                    }
                }
            }
            R.id.menu_ordenar_produtos_descricao_asc -> {
                lifecycleScope.launch {
                    produtoDao.ordenarPorDescricaoAsc().collect() {
                        adapter.setData(it)
                    }
                }
            }
            R.id.menu_ordenar_produtos_descricao_desc -> {
                lifecycleScope.launch {
                    produtoDao.ordenarPorDescricaoDesc().collect() {
                        adapter.setData(it)
                    }
                }
            }
            R.id.menu_ordenar_produtos_valor_asc -> {
                lifecycleScope.launch {
                    produtoDao.ordenarPorValorAsc().collect() {
                        adapter.setData(it)
                    }
                }
            }
            R.id.menu_ordenar_produtos_valor_desc -> {
                lifecycleScope.launch {
                    produtoDao.ordenarPorValorDesc().collect() {
                        adapter.setData(it)
                    }
                }
            }
            R.id.menu_ordenar_produtos_sem_ordem -> {
                lifecycleScope.launch {
                    produtoDao.buscaTodos().collect() {
                        adapter.setData(it)
                    }
                }
            }
        }
    }
}


