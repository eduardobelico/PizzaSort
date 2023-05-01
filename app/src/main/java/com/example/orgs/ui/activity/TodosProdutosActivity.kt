package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityTodosProdutosBinding
import com.example.orgs.extensions.vaiPara
import com.example.orgs.model.Produto
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.example.orgs.ui.recyclerview.adapter.TituloProdutosUsuarioAdapter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TodosProdutosActivity : UsuarioBaseActivity() {

    private val binding by lazy {
        ActivityTodosProdutosBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityTodosProdutosRecyclerView
        lifecycleScope.launch {
            produtoDao.buscaTodos()
                .map { produtos ->
                produtos.sortedBy {
                    it.usuarioId
                }
                    .groupBy {
                        it.usuarioId
                    }.map { produtosUsuario ->
                        setTituloProdutosUsuarioAdapter(produtosUsuario)
                    }.flatten()
            }.collect { adapter ->
                    recyclerView.adapter = ConcatAdapter(adapter)
                }
        }
    }

    private fun setTituloProdutosUsuarioAdapter(produtosUsuario: Map.Entry<String?, List<Produto>>) =
        listOf(
            TituloProdutosUsuarioAdapter(this, listOf(produtosUsuario.key)),
            ListaProdutosAdapter(this, produtosUsuario.value)
            { produtoSelecionado ->
                vaiPara(DetalhesProdutoActivity::class.java) {
                    putExtra(CHAVE_PRODUTO_ID, produtoSelecionado.id)
                }
            }
        )


}