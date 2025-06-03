package com.example.drinkoff.model

data class Produto(
    val nome: String,
    val descricao: String,
    val preco: Double,
    val imagem: String,
    val restaurante: String // ← ESSENCIAL
)
