package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityDetalhesProdutoBinding
import com.example.orgs.extensions.formatToBrazilianCurrency
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private lateinit var produto: Produto
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::produto.isInitialized) {
            val db = AppDatabase.instancia(this)
            val produtoDao = db.produtoDao()
            when (item.itemId) {
                R.id.menu_detalhes_produto_remover -> {
                    produtoDao.remove(produto)
                    finish()
                }
                R.id.menu_detalhes_produto_editar -> {
                    Intent(this, FormularioProdutoActivity::class.java).apply {
                        putExtra(PRODUTO_CHAVE, produto)
                        startActivity(this)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(PRODUTO_CHAVE)?.let { produtoCarregado ->
            produto = produtoCarregado
            preencheCampos(produtoCarregado)
        } ?: finish()
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text = produtoCarregado.valor.formatToBrazilianCurrency()
        }
    }

}