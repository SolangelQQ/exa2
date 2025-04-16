package com.example.book.ui.book

import BookViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.book.domain.entity.Book
import com.example.book.R

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun BookSearchScreen(
    viewModel: BookViewModel = hiltViewModel(),
    onBookSelected: (Book) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search books") },
            trailingIcon = {
                IconButton(onClick = { viewModel.searchBooks(query) }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { viewModel.searchBooks(query) }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = uiState) {
            is BookUiState.Empty -> {
                Text("No results found", modifier = Modifier.fillMaxSize().wrapContentSize())
            }
            is BookUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is BookUiState.Error -> {
                Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
            }
            is BookUiState.Success -> {
                LazyColumn {
                    items(state.books) { book ->
                        BookItem(book, onBookSelected)
                    }
                }
            }
            else -> {
                Text("Unexpected state", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun BookItem(book: Book, onClick: (Book) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(book) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = book.coverUrl,
                contentDescription = "Book cover",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = painterResource(R.drawable.ic_book_placeholder),
                error = painterResource(R.drawable.ic_book_placeholder)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                book.author?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                book.publishYear?.let {
                    Text(
                        text = it.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}