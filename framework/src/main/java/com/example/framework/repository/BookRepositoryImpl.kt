package com.example.framework.repository

import com.example.framework.service.BookApiService
import com.example.book.domain.entity.Book

import com.example.domain.repository.BookRepository
import com.example.framework.persistence.BookDao
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookApiService: BookApiService,
    private val bookDao: BookDao
) : BookRepository {
    override suspend fun searchBooks(query: String): List<Book> {
        val response = bookApiService.searchBooks(query)
        return if (response.isSuccessful) {
            response.body()?.books?.map { it.toDomain() } ?: emptyList()
        } else {
            emptyList()
        }
    }
}