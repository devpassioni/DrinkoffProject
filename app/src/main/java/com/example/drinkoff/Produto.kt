package com.example.drinkoff

data class Produto(
    val nome: String,
    val descricao: String,
    val imagem: Int, // R.drawable...
    val categoria: String,
    val preco: Double
)