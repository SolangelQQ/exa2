//package com.example.book.ui.book
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.book.domain.entity.Book
//import com.example.domain.usecase.SearchBooksUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class BookViewModel @Inject constructor(
//    private val searchBooksUseCase: SearchBooksUseCase
//) : ViewModel() {
//    private val _uiState = mutableStateOf<BookUiState>(BookUiState.Empty)
//    val uiState: State<BookUiState> = _uiState
//
//    fun searchBooks(query: String) {
//        viewModelScope.launch {
//            _uiState.value = BookUiState.Loading
//            try {
//                val books = searchBooksUseCase(query)
//                _uiState.value = if (books.isEmpty()) {
//                    BookUiState.Empty
//                } else {
//                    BookUiState.Success(books)
//                }
//            } catch (e: Exception) {
//                _uiState.value = BookUiState.Error(e.message ?: "Unknown error")
//            }
//        }
//    }
//}
//
//sealed class BookUiState {
//    object Empty : BookUiState()
//    object Loading : BookUiState()
//    data class Error(val message: String) : BookUiState()
//    data class Success(val books: List<Book>) : BookUiState()
//}
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book.domain.entity.Book
import com.example.domain.usecase.SearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.Immutable

@HiltViewModel
class BookViewModel @Inject constructor(
    private val searchBooksUseCase: SearchBooksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.Empty)
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _uiState.value = BookUiState.Loading
            try {
                val books = searchBooksUseCase(query)
                _uiState.value = if (books.isEmpty()) {
                    BookUiState.Empty
                } else {
                    BookUiState.Success(books)
                }
            } catch (e: Exception) {
                _uiState.value = BookUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

@Immutable
sealed class BookUiState {
    object Empty : BookUiState()
    object Loading : BookUiState()
    data class Error(val message: String) : BookUiState()
    data class Success(val books: List<Book>) : BookUiState()
}