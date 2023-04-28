package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.extensions.vaiPara
import com.example.orgs.preferences.dataStore
import com.example.orgs.preferences.usuarioLogadoPreferences
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.launch

class ListaProdutosActivity : AppCompatActivity() {

    private val adapter = ListaProdutosAdapter(context = this@ListaProdutosActivity)
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }
    private val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
        lifecycleScope.launch {
            launch {
                produtoDao.buscaTodos().collect() { produtos ->
                    adapter.setData(produtos)
                }
            }
            launch {
                dataStore.data.collect() { preferences ->
                    preferences[usuarioLogadoPreferences]?.let { usuarioId ->
                        launch {
                            usuarioDao.buscaUsuarioPorId(usuarioId).collect() {
                                Log.i("ListaProdutos", "onCreate: $it")
                            }
                        }
                    }?: vaiParaLogin()
                }
            }
//            launch {
//                atualizaTela()
//            }
        }
    }

    private fun vaiParaLogin() {
        vaiPara(LoginActivity::class.java)
        finish()
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

    protected suspend fun deslogaUsuario() {
        dataStore.edit { preferences ->
            preferences.remove(usuarioLogadoPreferences)
        }
    }

//    private suspend fun atualizaTela() {
//        usuario.filterNotNull().collect { usuario ->
//            produtoDao.buscaProdutosUsuario(usuario.id).collect {
//                adapter.setData(it)
//            }
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMenuOrganizar(item)
        return super.onOptionsItemSelected(item)
    }

    private fun setMenuOrganizar(item: MenuItem) {
        when (item.itemId) {

            R.id.menu_logout -> {
                lifecycleScope.launch {
                    deslogaUsuario()
                }
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


