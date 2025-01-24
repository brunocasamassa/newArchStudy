package com.example.newarchstudy.data.models.dto

//DTO = Objeto bruto, sem ser modificado
//ENTITY = objeto modificado para atender a aplicacao
data class ResponseNews(
    val news: List<ResponseNew>? = null,
    val status: String? = null
)