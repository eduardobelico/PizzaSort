package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.extensions.formatToBrazilianCurrency
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto

//1 - Primeiro crio o meu adapter - é o adapter que dá acesso aos dados. É ele que cria objetos ViewHolder conforme necessário e também define os dados para essas visualizações.
class ListaProdutosAdapter(
    val context: Context,
    var clicarNoProduto: (produto: Produto) -> Unit = {},
    var clicarEmEditar: (produto: Produto) -> Unit = {},
    var clicarEmRemover: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ProdutoViewHolder>() {

    //2 - Imediatamente depois crio o ViewHolder!! Apenas depois de criado, adiciono as funções do onCreate, onBindView e getItemCount! É o viewHolder que dá funcionalidade aos itens da lista. Ele segura as informações da View.
    inner class ProdutoViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {
        fun bindView(produto: Produto) {
            this.produto = produto
            with(binding) {
                produtoItemNome.text = produto.nome
                produtoItemDescricao.text = produto.descricao
                produtoItemValor.text = produto.valor.formatToBrazilianCurrency()
                imageView.tentaCarregarImagem(produto.imagem)
            }
        }

        private lateinit var produto: Produto

        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    clicarNoProduto(produto)
                }
            }
            itemView.setOnLongClickListener {
                showPopup(it)
                true
            }
        }

        fun showPopup(v: View) {
            PopupMenu(context, v).apply {
                inflate(R.menu.menu_detalhes_produto)
                setOnMenuItemClickListener(this@ProdutoViewHolder)
                show()
            }
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when (it.itemId) {
                    R.id.menu_detalhes_produto_editar -> {
                        clicarEmEditar(produto)
                    }
                    R.id.menu_detalhes_produto_remover -> {
                        clicarEmRemover(produto)
                    }
                }
            }
            return true
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