package model
data class Restaurante(
    val nome: String,
    val imagem: String,
    val categorias: Map<String, List<String>>
)