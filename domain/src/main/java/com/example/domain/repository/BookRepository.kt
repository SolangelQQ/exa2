package com.example.domain.repository

import com.example.book.domain.entity.Book

interface BookRepository {
    suspend fun searchBooks(query: String): List<Book>
}