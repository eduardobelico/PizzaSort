//package com.example.orgs.ui.recyclerview.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.orgs.databinding.ProdutoItemBinding
//import com.example.orgs.model.Produto
//
//class ListaJorgeProdutosAdapter(
//    private val context: Context,
//    produtos: List<Produto>
//) : RecyclerView.Adapter<ListaJorgeProdutosAdapter.ViewHolder>() {
//
//    private val produtos = produtos.toMutableList()
//
//    inner class ViewHolder(private val binding: ProdutoItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun vincula(produto: Produto) {
//            val nome = binding.produtoItemNome
//            nome.text = produto.nome
//            val descricao = binding.produtoItemDescricao
//            descricao.text = produto.descricao
//            val valor = binding.produtoItemValor
//            valor.text = produto.valor.toPlainString()
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
//
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val produto = produtos[position]
//        holder.vincula(produto)
//    }
//
//    override fun getItemCount(): Int = produtos.size
//
//    fun atualiza(produtos: List<Produto>) {
//        this.produtos.clear()
//        this.produtos.addAll(produtos)
//        notifyDataSetChanged()
//    }
//
//}
