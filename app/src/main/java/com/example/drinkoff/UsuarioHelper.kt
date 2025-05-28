package com.example.drinkoff

import android.content.Context
import com.google.gson.Gson
import java.io.File

fun adicionarUsuario(users: Users, context: Context) {
    val gson = Gson()
    val file = File(context.filesDir, "usuarios.json")

    val usuariosExistentes = if (file.exists()) {
        val json = file.readText()
        gson.fromJson(json, Array<Users>::class.java)?.toMutableList() ?: mutableListOf()
    } else {
        mutableListOf()
    }

    usuariosExistentes.add(users)

    val jsonAtualizado = gson.toJson(usuariosExistentes)
    file.writeText(jsonAtualizado)
}