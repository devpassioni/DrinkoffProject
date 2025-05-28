package com.example.drinkoff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ProdutoAdapter(private val produtos: List<Produto>) :
    RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduto: ImageView = itemView.findViewById(R.id.imgProduto)
        val txtNome: TextView = itemView.findViewById(R.id.txtNome)
        val txtDescricao: TextView = itemView.findViewById(R.id.txtDescricao)
        val txtPreco: TextView = itemView.findViewById(R.id.txtPreco)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.imgProduto.setImageResource(produto.imagem)
        holder.txtNome.text = produto.nome
        holder.txtDescricao.text = produto.descricao
        holder.txtPreco.text = "R$ %.2f".format(produto.preco)
    }

    override fun getItemCount(): Int = produtos.size
}
