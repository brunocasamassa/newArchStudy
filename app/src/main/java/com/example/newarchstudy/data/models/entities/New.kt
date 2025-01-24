package com.example.newarchstudy.data.models.entities


//DTO = Objeto bruto, sem ser modificado
//ENTITY = objeto modificado para atender a aplicacao

/**
 * NESTE CASO , é uma entity pois foi removido atributos nao necessarios na aplicacao após o request(published e id)
 * "objeto limpo"
 *
 * */
data class New(
    val author: String,
    val category: List<String>,
    val description: String,
    val image: String,
    val language: String,
    val title: String,
    val url: String
)