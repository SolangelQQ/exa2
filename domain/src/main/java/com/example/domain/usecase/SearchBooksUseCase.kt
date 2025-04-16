package com.example.domain.usecase

import com.example.book.domain.entity.Book
import com.example.domain.repository.BookRepository

class SearchBooksUseCase(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(query: String): List<Book> {
        return bookRepository.searchBooks(query)
    }
}