package com.example.orgs.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.extensions.formatToBrazilianCurrency
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto

//1 - Primeiro crio o meu adapter - é o adapter que dá acesso aos dados. É ele que cria objetos ViewHolder conforme necessário e também define os dados para essas visualizações.
class ListaProdutosAdapter : RecyclerView.Adapter<ListaProdutosAdapter.ProdutoViewHolder>() {

    //2 - Imediatamente depois crio o ViewHolder!! Apenas depois de criado, adiciono as funções do onCreate, onBindView e getItemCount! É o viewHolder que dá funcionalidade aos itens da lista. Ele segura as informações da View.
    inner class ProdutoViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(produto: Produto) {
            with(binding) {
                produtoItemNome.text = produto.nome
                produtoItemDescricao.text = produto.descricao
                produtoItemValor.text = produto.valor.formatToBrazilianCurrency()

                val visibilidade = if (produto.imagem != null) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

                imageView.visibility = visibilidade
                imageView.tentaCarregarImagem(produto.imagem)
            }
        }
    }

    //3 - Crio a variável que vai funcionar como a mutableList
    private val produtoList = mutableListOf<Produto>()

    //5 - Criando o inflater no onCreate! No parâmetro do from só trago o context pelo parent
    //6 - Crio a variável binding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)

        return ProdutoViewHolder(binding)
    }

    //4 - Com a minha lista, determino o seu tamanho através do .size
    override fun getItemCount(): Int = produtoList.size

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) =
        holder.bindView(produtoList[position])

    fun setData(novaProdutoList: List<Produto>) {
        notifyItemRangeRemoved(0, produtoList.size)
        produtoList.clear()
        produtoList.addAll(novaProdutoList)
        notifyItemInserted(novaProdutoList.size)
    }


}