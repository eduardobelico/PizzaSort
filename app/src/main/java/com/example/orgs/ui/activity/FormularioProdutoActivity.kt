package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var idProduto = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
        setContentView(binding.root)
        title = "Cadastrar Pizza"
        binding.activityFormularioProdutoImagem.setOnClickListener() {
            FormularioImagemDialog(this)
                .showDialog(url) { imagem ->
                    url = imagem
                    binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
                }
        }
        intent.getParcelableExtra<Produto>(PRODUTO_CHAVE)?.let { produtoCarregado ->
            title = "Alterar Produto"
            idProduto = produtoCarregado.id
            with(binding) {
                activityFormularioProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
                activityFormularioProdutoNome.setText(produtoCarregado.nome)
                activityFormularioProdutoDescricao.setText(produtoCarregado.descricao)
                activityFormularioProdutoValor.setText(produtoCarregado.valor.toPlainString())
            }
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()

        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            produtoDao.salva(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}