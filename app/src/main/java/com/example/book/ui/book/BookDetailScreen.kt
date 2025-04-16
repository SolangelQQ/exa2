package com.example.book.ui.book

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BookDetailScreen(bookId: String?) {
    Text(text = "Detalles del libro: $bookId")
}