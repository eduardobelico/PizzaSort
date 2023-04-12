package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf(
        Produto(nome = "Pizza Calabresa",
        descricao = "queijo mussarela, tomate, cebola e calabresa",
        valor = BigDecimal("29.90")
        )
        )
    }
}