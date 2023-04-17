package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.databinding.ActivityDetalhesProdutoBinding
import com.example.orgs.extensions.formatToBrazilianCurrency
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private val binding by lazy{
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
        when(item.itemId){
            R.id.menu_detalhes_produto_remover -> {

            }
            R.id.menu_detalhes_produto_editar -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(PRODUTO_CHAVE)?.let {
            produtoCarregado -> preencheCampos(produtoCarregado)
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