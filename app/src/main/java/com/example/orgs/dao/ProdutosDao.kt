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

            Produto(
                nome = "Pizza Calabresa",
                descricao = "queijo mussarela, tomate, cebola e calabresa",
                valor = BigDecimal("29.90")
            ),
            Produto(
                nome = "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.",
                descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec ultrices tincidunt arcu non sodales neque. Pulvinar elementum integer enim neque volutpat ac. Vitae tempus quam pellentesque nec nam. Habitant morbi tristique senectus et netus et malesuada. Semper quis lectus nulla at. Sed enim ut sem viverra. Sed lectus vestibulum mattis ullamcorper velit sed ullamcorper morbi tincidunt. Faucibus vitae aliquet nec ullamcorper sit amet risus nullam. Rhoncus urna neque viverra justo nec ultrices dui. Amet massa vitae tortor condimentum lacinia quis vel eros donec. Diam quis enim lobortis scelerisque fermentum dui faucibus in ornare. Eget velit aliquet sagittis id consectetur. Neque vitae tempus quam pellentesque nec nam aliquam. Scelerisque fermentum dui faucibus in ornare quam viverra. Pellentesque dignissim enim sit amet venenatis urna cursus eget nunc. Mattis nunc sed blandit libero volutpat sed cras ornare arcu. Est ante in nibh mauris. Morbi enim nunc faucibus a. Ut faucibus pulvinar elementum integer enim.",
                valor = BigDecimal("45.90"),
                imagem = "https://images.pexels.com/photos/2471171/pexels-photo-2471171.jpeg"
            )
        )


    }
}