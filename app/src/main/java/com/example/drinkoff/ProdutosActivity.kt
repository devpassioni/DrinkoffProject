package com.example.drinkoff


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkoff.databinding.ActivityProdutosBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProdutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val produtos = loadProdutosFromAssets()
        setupRecyclerView(produtos)
    }

    private fun loadProdutosFromAssets(): List<Produto> {
        val jsonString: String
        try {
            jsonString = assets.open("produtos.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val listProdutoType = object : TypeToken<List<Produto>>() {}.type
        return Gson().fromJson(jsonString, listProdutoType)
    }

    private fun setupRecyclerView(produtos: List<Produto>) {
        val adapter = ProdutoAdapter(produtos)
        binding.recyclerViewProdutos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProdutos.adapter = adapter
    }
}
