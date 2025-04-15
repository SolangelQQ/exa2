package com.example.book.domain.entity

data class Book(
    val id: String,
    val title: String,
    val author: String?,
    val coverUrl: String?,
    val publishYear: Int?,
    val description: String?
)