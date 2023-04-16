package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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