//package com.example.orgs.ui.recyclerview.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.orgs.databinding.ProdutoItemBinding
//import com.example.orgs.model.Produto
//
//class AdapterTeste : RecyclerView.Adapter<AdapterTeste.TesteVh>() {
//
//    private val produtoTesteLista = mutableListOf<Produto>()
//
//    inner class TesteVh(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder() {
//
//        fun bindView(produto: Produto) {
//            with(binding) {
//                produtoItemNome.text = produto.nome
//                produtoItemDescricao.text = produto.descricao
//                produtoItemValor.text = produto.valor.toPlainString()
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TesteVh {
//        val inflater = LayoutInflater.from(parent.context)
//
//        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
//
//        return TesteVh(binding)
//    }
//
//    override fun getItemCount(): Int = produtoTesteLista.size
//
//    override fun onBindViewHolder(holder: TesteVh, position: Int) =
//        holder.bindView(produtoTesteLista[position])
//
//    fun setData(novaListaProdutos: List<Produto>){
//        notifyItemRangeRemoved(0,produtoTesteLista.size)
//        produtoTesteLista.clear()
//        produtoTesteLista.addAll(novaListaProdutos)
//        notifyItemInserted(produtoTesteLista.size)
//    }
//}