package com.example.data.repository

import com.example.book.domain.entity.Book

interface BookRepository {
    suspend fun searchBooks(query: String): List<Book>
}