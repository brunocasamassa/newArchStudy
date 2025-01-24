package com.example.newarchstudy.data.models.entities

import com.example.newarchstudy.data.models.dto.ResponseNew

//DTO = Objeto bruto, sem ser modificado
//ENTITY = objeto modificado para atender a aplicacao
data class News(
    val news: ArrayList<New>? = null,
    val status: String? = null
)