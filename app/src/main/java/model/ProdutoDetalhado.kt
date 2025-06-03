package model

data class ProdutoDetalhado(
    val nome: String,
    val descricao: String,
    val preco: Double,
    val imagem: String,
    val restaurante: String,
    val categoria: String // novo campo
)