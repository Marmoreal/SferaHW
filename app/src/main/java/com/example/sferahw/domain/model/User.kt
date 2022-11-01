package com.example.sferahw.domain.model

data class User(
    val id: Int,
    val name: String,
    val url: String,
    val isSubscribed: Boolean = false
)
